package com.example.buyshared.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.ListResponse
import com.example.buyshared.data.retrofitObjet.User
import com.example.buyshared.domain.usecase.InsertDBUseCase
import com.example.buyshared.domain.usecase.LoadEventUseCase
import com.example.buyshared.domain.usecase.LoadListsUseCase
import com.example.buyshared.domain.usecase.getAllEventsDBUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadEventUseCase: LoadEventUseCase,
    private val loadListsUseCase: LoadListsUseCase,
    private val insertDBUseCase: InsertDBUseCase,
    private val getAllEventsDBUseCase: getAllEventsDBUseCase
): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isLoadingView = MutableLiveData<Int>(View.GONE)
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"
    val isLoadEvent = MutableLiveData<Boolean>()
    val eventView = MutableLiveData<Int>(View.VISIBLE)
    val listView = MutableLiveData<Int>(View.VISIBLE)
    val listEvents = MutableLiveData<List<EventsEntity>>()

    fun loadEventos(context:Context){
        isLoading.postValue(true)
        isLoadingView.postValue(View.VISIBLE)
        tinyDB = TinyDB(context)
        val user = tinyDB.getObject("user",User::class.java)
        viewModelScope.launch {
            val result: EventsResponse? = loadEventUseCase(user._id, context)
            Log.v(logi, "success:" + result)
            Log.v(logi,"cantidad eventos:"+result!!.size)

            if(result!!.size === 0){
                eventView.postValue(View.GONE)
                Log.v(logi,"eventos vacio")
            }else{
                eventView.postValue(View.VISIBLE)
                for (objeto in result!!) {
                    Log.v(logi,"objeto2:"+ objeto.nombre)
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
                            objeto.complet
                        )
                    )
                }
                val getAllEvent = getAllEventsDBUseCase.invoke()
                listEvents.postValue(getAllEvent)
                Log.v(logi,"getAllEvents:"+getAllEvent.size)
            }
            isLoading.postValue(false)
            isLoadingView.postValue(View.GONE)
        }
    }

    fun loadLists(context:Context){
        isLoading.postValue(true)
        isLoadingView.postValue(View.VISIBLE)
        tinyDB = TinyDB(context)
        val user = tinyDB.getObject("user",User::class.java)
        viewModelScope.launch {
            val result: ListResponse? = loadListsUseCase(user._id, context)
            Log.v(logi, "success:" + result)
            isLoading.postValue(false)
            isLoadingView.postValue(View.GONE)
            if(result!!.isEmpty()){
                listView.postValue(View.GONE)
            }else{
                listView.postValue(View.VISIBLE)
            }

        }
    }

}