package com.example.buyshared.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.ListsResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.User
import com.example.buyshared.domain.usecase.CleanEventsDB
import com.example.buyshared.domain.usecase.CleanListsDBUseCase
import com.example.buyshared.domain.usecase.GetEventByIdUserCase
import com.example.buyshared.domain.usecase.InsertDBUseCase
import com.example.buyshared.domain.usecase.InsertListRetrofitUseCase
import com.example.buyshared.domain.usecase.InsertListsDBUseCase
import com.example.buyshared.domain.usecase.InsetEventRetrofitUseCase
import com.example.buyshared.domain.usecase.LoadEventUseCase
import com.example.buyshared.domain.usecase.LoadListsUseCase
import com.example.buyshared.domain.usecase.getAllEventsDBUseCase
import com.example.buyshared.domain.usecase.getAllListsDBUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadEventUseCase: LoadEventUseCase,
    private val loadListsUseCase: LoadListsUseCase,
    private val insertDBUseCase: InsertDBUseCase,
    private val getAllEventsDBUseCase: getAllEventsDBUseCase,
    private val cleanEventsDB: CleanEventsDB,
    private val cleanListsDBUseCase: CleanListsDBUseCase,
    private val insertListsDBUseCase: InsertListsDBUseCase,
    private val getAllListsDBUseCase: getAllListsDBUseCase,
    private val insertListRetrofitUseCase: InsertListRetrofitUseCase,
    private val insertEventRetrofitUseCase: InsetEventRetrofitUseCase,
    private val getEventByIdUserCase: GetEventByIdUserCase
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isLoadingView = MutableLiveData<Int>(View.GONE)
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"
    val isLoadEvent = MutableLiveData<Boolean>()
    val eventView = MutableLiveData<Int>(View.VISIBLE)
    val listView = MutableLiveData<Int>(View.VISIBLE)
    val listEvents = MutableLiveData<List<EventsEntity>>()
    val listLists = MutableLiveData<List<ListsEntity>>()
    val isInsert = MutableLiveData<Boolean>()

    fun loadEventos(context: Context) {
        isLoading.postValue(true)
        isLoadingView.postValue(View.VISIBLE)
        tinyDB = TinyDB(context)
        val user = tinyDB.getObject("user", User::class.java)
        viewModelScope.launch {
            val result: EventsResponse? = loadEventUseCase(user._id, context)
            Log.v(logi, "success:" + result)
            Log.v(logi, "cantidad eventos:" + result!!.size)

            if (result!!.size === 0) {
                eventView.postValue(View.GONE)
                Log.v(logi, "eventos vacio")
            } else {
                eventView.postValue(View.VISIBLE)
                cleanEventsDB.invoke()
                for (objeto in result!!) {
                    Log.v(logi, "objeto2:" + objeto.nombre)
                    insertDBUseCase(
                        EventsEntity(
                            0,
                            objeto._id,
                            objeto.id_user,
                            objeto.nombre,
                            objeto.bg,
                            objeto.estado,
                            objeto.referencia,
                            objeto.__v,
                            objeto.cant,
                            objeto.complet,
                            objeto.taskcomplet.toInt()
                        )
                    )
                }
                val getAllEvent = getAllEventsDBUseCase.invoke()
                listEvents.postValue(getAllEvent)
                Log.v(logi, "getAllEvents:" + getAllEvent.size)
            }
            isLoading.postValue(false)
            isLoadingView.postValue(View.GONE)
        }
    }

    fun loadLists(context: Context) {
        isLoading.postValue(true)
        isLoadingView.postValue(View.VISIBLE)
        tinyDB = TinyDB(context)
        val user = tinyDB.getObject("user", User::class.java)
        viewModelScope.launch {
            val result: ListsResponse? = loadListsUseCase(user._id, context)
            Log.v(logi, "success:" + result)
            isLoading.postValue(false)
            isLoadingView.postValue(View.GONE)
            if (result!!.isEmpty()) {
                listView.postValue(View.GONE)
            } else {
                listView.postValue(View.VISIBLE)
                cleanListsDBUseCase.invoke()

                for (objeto in result!!) {
                    Log.v(logi, "objeto list:" + objeto.nombre)
                    insertListsDBUseCase(
                        ListsEntity(
                            0,
                            objeto._id,
                            objeto.__v,
                            objeto.cant,
                            objeto.nombre,
                            objeto.referencia,
                            objeto.estado,
                            objeto.id_user
                        )
                    )
                }

                val getAllList = getAllListsDBUseCase.invoke()
                listLists.postValue(getAllList)
                Log.v(logi, "getAllLists:" + getAllList.size)
            }
        }
    }

    fun insertListRetrofit(
        nombre: String,
        estado: String,
        id_event: String,
        referencia: String
    ) {
        viewModelScope.launch {
            val result: InsertListResponse? =
                insertListRetrofitUseCase.invoke(nombre, estado, id_event, referencia)
            isInsert.postValue(true)
            Log.v(logi, "success:" + result)
            if (result!!.success) {
                isInsert.postValue(false)
                Log.v(logi, "success:" + result)
                insertListsDBUseCase.invoke(
                    ListsEntity(
                        0,
                        result!!._id,
                        result!!.__v,
                        result!!.cant,
                        result!!.nombre,
                        result!!.referencia,
                        result!!.estado,
                        result!!.id_user
                    )
                )
                val getAllList = getAllListsDBUseCase.invoke()
                listLists.postValue(getAllList)
            }
        }
    }

    fun inserEventRetrofit(nombre: String, file: File) {
        viewModelScope.launch {
            val result: InsertEventResponse? = insertEventRetrofitUseCase.invoke(nombre, file)
            Log.v(logi, "success:" + result)
        }
    }

    fun getInfoEventById(id:String): EventsEntity? {
        return getEventByIdUserCase.invoke(id)
    }


}