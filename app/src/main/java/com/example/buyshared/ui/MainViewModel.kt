package com.example.buyshared.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.data.retrofitObjet.DelListResponse
import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import com.example.buyshared.data.retrofitObjet.EventDetailResponse
import com.example.buyshared.data.retrofitObjet.EventsResponse2
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.ListsResponse
import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import com.example.buyshared.data.retrofitObjet.SolicitudResponse
import com.example.buyshared.data.retrofitObjet.Task
import com.example.buyshared.data.retrofitObjet.TaskCompletResponse
import com.example.buyshared.data.retrofitObjet.User
import com.example.buyshared.domain.usecase.AddSolicitudUseCase
import com.example.buyshared.domain.usecase.CleanEventsDB
import com.example.buyshared.domain.usecase.CleanListsDBUseCase
import com.example.buyshared.domain.usecase.CleanTasksDBUserCase
import com.example.buyshared.domain.usecase.CompletTaskDBUserCase
import com.example.buyshared.domain.usecase.CompletTaskUseCase
import com.example.buyshared.domain.usecase.DelEventUseCase
import com.example.buyshared.domain.usecase.DelListUseCase
import com.example.buyshared.domain.usecase.DelTaskDBUseCase
import com.example.buyshared.domain.usecase.DelTaskRetrofitUseCase
import com.example.buyshared.domain.usecase.DelTasksDBByIdEventUserCase
import com.example.buyshared.domain.usecase.DelTasksDBByIdListUserCase
import com.example.buyshared.domain.usecase.DeleteAvatarByIdEventUseCase
import com.example.buyshared.domain.usecase.DeleteAvatarByIdListUseCase
import com.example.buyshared.domain.usecase.GetAllAvatarsDB
import com.example.buyshared.domain.usecase.GetEventByIdUserCase
import com.example.buyshared.domain.usecase.GetListByIdUserCase
import com.example.buyshared.domain.usecase.InserTaskDBUserCase
import com.example.buyshared.domain.usecase.InsertAvatarsDBUseCase
import com.example.buyshared.domain.usecase.InsertDBUseCase
import com.example.buyshared.domain.usecase.InsertListRetrofitUseCase
import com.example.buyshared.domain.usecase.InsertListsDBUseCase
import com.example.buyshared.domain.usecase.InsertTaskRetrofitUseCase
import com.example.buyshared.domain.usecase.InsetEventRetrofitUseCase
import com.example.buyshared.domain.usecase.LoadAvatarByIdEventUseCase
import com.example.buyshared.domain.usecase.LoadAvatarByIdListUseCase
import com.example.buyshared.domain.usecase.LoadAvatarsListRetrofitUseCase
import com.example.buyshared.domain.usecase.LoadEventDetailUseCase
import com.example.buyshared.domain.usecase.LoadEventUseCase
import com.example.buyshared.domain.usecase.LoadListTaskRetrofitUserCase
import com.example.buyshared.domain.usecase.LoadListsUseCase
import com.example.buyshared.domain.usecase.LoadTaskByIdEventUserCase
import com.example.buyshared.domain.usecase.LoadTaskByIdListUserCase
import com.example.buyshared.domain.usecase.getAllEventsDBUseCase
import com.example.buyshared.domain.usecase.getAllListsDBUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private val getEventByIdUserCase: GetEventByIdUserCase,
    private val getListByIdUserCase: GetListByIdUserCase,
    private val loadListTaskRetrofitUserCase: LoadListTaskRetrofitUserCase,
    private val insertTaskDBUserCase: InserTaskDBUserCase,
    private val loadTaskByIdListUserCase: LoadTaskByIdListUserCase,
    private val cleanTasksDBUserCase: CleanTasksDBUserCase,
    private val delTasksDBByIdListUserCase: DelTasksDBByIdListUserCase,
    private val loadEventDetailUseCase: LoadEventDetailUseCase,
    private val loadTaskByIdEventUserCase: LoadTaskByIdEventUserCase,
    private val delTasksDBByIdEventUserCase: DelTasksDBByIdEventUserCase,
    private val completTaskUseCase: CompletTaskUseCase,
    private val completTaskDBUserCase: CompletTaskDBUserCase,
    private val loadAvatarsListRetrofitUseCase: LoadAvatarsListRetrofitUseCase,
    private val insertAvatarsDBUseCase: InsertAvatarsDBUseCase,
    private val loadAvatarByIdList: LoadAvatarByIdListUseCase,
    private val getAllAvatarsDB: GetAllAvatarsDB,
    private val deleteAvatarByIdListUseCase: DeleteAvatarByIdListUseCase,
    private val deleteAvatarByIdEventUseCase: DeleteAvatarByIdEventUseCase,
    private val loadAvatarByIdEventUseCase: LoadAvatarByIdEventUseCase,
    private val insertTaskRetrofitUseCase: InsertTaskRetrofitUseCase,
    private val delTaskRetrofitUseCase: DelTaskRetrofitUseCase,
    private val delTaskDBUseCase: DelTaskDBUseCase,
    private val addSolicitudUseCase: AddSolicitudUseCase,
    private val delListUseCase: DelListUseCase,
    private val delEventUseCase: DelEventUseCase
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isInserterEvent = MutableLiveData<Boolean>()
    val isLoadingTask = MutableLiveData<Boolean>()
    val isEdited = MutableLiveData<Boolean>()
    val isLoadingView = MutableLiveData<Int>(View.GONE)
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"
    val isLoadEvent = MutableLiveData<Boolean>()
    val isDel = MutableLiveData<Boolean>()
    val eventView = MutableLiveData<Int>(View.VISIBLE)
    val listView = MutableLiveData<Int>(View.VISIBLE)
    val listEvents = MutableLiveData<List<EventsEntity>>()
    val listLists = MutableLiveData<List<ListsEntity>>()
    val isInsert = MutableLiveData<Boolean>()
    val isLoadTask = MutableLiveData<Boolean>()
    val listTasks = MutableLiveData<List<TaskEntity>>()
    val listAvatarsList = MutableLiveData<List<Avatar>>()
    val positionEdit = MutableLiveData<Int>(null)
    lateinit var listTask: List<TaskEntity>

    fun loadEventos(context: Context) {
        isLoading.postValue(true)
        isLoadingView.postValue(View.VISIBLE)
        tinyDB = TinyDB(context)
        val user = tinyDB.getObject("user", User::class.java)
        viewModelScope.launch {
            val result: EventsResponse2? = loadEventUseCase(user._id, context)
            Log.v(logi, "success:" + result)
            Log.v(logi, "cantidad eventos:" + result!!.size)

            if (result!!.size === 0) {
                eventView.postValue(View.GONE)
                Log.v(logi, "eventos vacio")
            } else {
                eventView.postValue(View.VISIBLE)
                cleanEventsDB.invoke()
                for (objeto in result!!) {
                    Log.v(logi, "Evento:" + objeto.nombre)
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
                    Log.v(logi, "Event id:" + objeto._id)
                    deleteAvatarByIdEventUseCase(objeto._id)
                    for (avatar in objeto.avatars) {
                        Log.v(logi, "Event avatar id:" + avatar.idEvent)
                        insertAvatarsDBUseCase(avatar.toAvatarEntity())
                    }
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
            if (result!!.Lists.isEmpty()) {
                listView.postValue(View.GONE)
            } else {
                listView.postValue(View.VISIBLE)
                cleanListsDBUseCase.invoke()

                for (objeto in result!!.Lists) {
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
                    deleteAvatarByIdListUseCase(objeto._id)

                    for (avatar in objeto.avatars) {
                        insertAvatarsDBUseCase(avatar.toAvatarEntity())
                    }
                }

                val getAllList = getAllListsDBUseCase.invoke()
                listLists.postValue(getAllList)
                Log.v(logi, "getAllLists:" + getAllList.size)
            }
        }
    }

    fun insertListRetrofit(
        nombre: String, estado: String, id_event: String, referencia: String
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

    fun inserEventRetrofit(nombre: String, file: Uri,context:Context) {
        isLoading.postValue(true)
        isInserterEvent.postValue(true)
        viewModelScope.launch {
            isLoading.postValue(false)
            val result: InsertEventResponse? = insertEventRetrofitUseCase.invoke(nombre, file, context)
            Log.v(logi, "success:" + result)
            insertDBUseCase(
                EventsEntity(
                    0,
                    result!!._id,
                    result.id_user,
                    result.nombre,
                    result.bg,
                    result.estado,
                    result.referencia,
                    result.__v,
                    result.cant,
                    result.complet,
                   0
                )
            )
            val getAllEvent = getAllEventsDBUseCase.invoke()
            listEvents.postValue(getAllEvent)
            isInserterEvent.postValue(false)
            Log.v(logi, "getAllEvents:" + getAllEvent.size)
        }
    }

    fun getInfoEventById(id: String): EventsEntity? {
        return getEventByIdUserCase.invoke(id)
    }

    fun getInfoListById(id: String): ListsEntity? {
        return getListByIdUserCase.invoke(id)
    }

    fun loadTaskList(idList: String) {
        isLoadTask.postValue(true)
        viewModelScope.launch {
            val result: LoadListDetailResponse? = loadListTaskRetrofitUserCase.invoke(idList)
            Log.v(logi, "success:" + result)
            isLoadTask.postValue(false)
            delTasksDBByIdListUserCase(idList)

            for (task in result!!.tasks) {
                Log.v(logi, "task:" + task.texto)
                insertTaskDBUserCase.invoke(task.toTaskEntity())
            }
            listAvatarsList.postValue(result!!.avatarList)
            val listTask = loadTaskByIdListUserCase.invoke(idList)
            listTasks.postValue(listTask)
            Log.v(logi, "cantidad de tareas:" + listTask.size)
        }
    }

    fun loadEventDetail(idEvent: String) {
        isLoadEvent.postValue(true)
        viewModelScope.launch {
            isLoadEvent.postValue(false)
            val result: EventDetailResponse? = loadEventDetailUseCase.invoke(idEvent)
            Log.v(logi, "success:" + result)
            delTasksDBByIdEventUserCase(idEvent)

            for (task in result!!.task) {
                Log.v(logi, "task:" + task.texto)
                insertTaskDBUserCase.invoke(task.toTaskEntity())
            }

            listAvatarsList.postValue(result!!.avatar)

            val listTask = loadTaskByIdEventUserCase.invoke(idEvent)
            listTasks.postValue(listTask)
            Log.v(logi, "cantidad de tareas:" + listTask.size)
        }
    }

    fun completTask(idTask: String, position: Int, requireActivity: FragmentActivity) {
        isLoadingTask.postValue(true)
        isEdited.postValue(false)
        tinyDB = TinyDB(requireActivity)
        viewModelScope.launch {
            val result: TaskCompletResponse? = completTaskUseCase(idTask)
            if (result!!.success) {
                isLoadingTask.postValue(false)
                completTaskDBUserCase(idTask, result!!.estado)

                var id = ""
                var listTask: List<TaskEntity>
                if (tinyDB.getString("typeSelect") === "event") {
                    id = tinyDB.getString("eventSel").toString()
                    listTask = loadTaskByIdEventUserCase.invoke(id!!)
                } else {
                    id = tinyDB.getString("listSel").toString()
                    listTask = loadTaskByIdListUserCase.invoke(id!!)
                }
                listTasks.postValue(listTask)

                positionEdit.postValue(position)
                isEdited.postValue(true)
            }
        }
    }

    fun loadAvatarsListRetrofit() {
        viewModelScope.launch {
            val result = loadAvatarsListRetrofitUseCase.invoke()
            Log.v(logi, "success:" + result)
        }
    }

    fun loadAvatarDBByIdList(_id: String): List<Avatar> {
        return loadAvatarByIdList(_id)
    }

    fun loadAvatarDBByIdEvent(_id: String): List<Avatar> {
        return loadAvatarByIdEventUseCase(_id)
    }

    suspend fun getAllAvatarsDBList(): List<AvatarsEntity> {
        return getAllAvatarsDB()
    }

    fun addTask(
        mostrar: String,
        idEvent: String,
        idList: String,
        referencia: String,
        task: String
    ) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val result: Task? = insertTaskRetrofitUseCase(idEvent, idList, referencia, task)
            insertTaskDBUserCase.invoke(result!!.toTaskEntity())
            isLoading.postValue(false)

            if (mostrar === "event") {
                listTask = loadTaskByIdEventUserCase.invoke(idEvent)
            } else {
                listTask = loadTaskByIdListUserCase.invoke(idList)
            }
            listTasks.postValue(listTask)
            Log.v(logi, "cantidad de tareas:" + listTask.size)
        }
    }

    fun delTaskRetrofit(id: String, mostrar: String, idLista: String, idEvent: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val result: DelTaskResponse? = delTaskRetrofitUseCase(id)
            isLoading.postValue(false)
            delTaskDBUseCase(id)
            Log.v("BuySharedLog", "mostrar:" + mostrar)
            Log.v("BuySharedLog", "mostrar lista:" + idLista)
            Log.v("BuySharedLog", "mostrar event:" + idEvent)
            if (mostrar === "event") {
                listTask = loadTaskByIdEventUserCase.invoke(idEvent)
            } else {
                listTask = loadTaskByIdListUserCase.invoke(idLista)
            }

            listTasks.postValue(listTask)
            Log.v(logi, "cantidad de tareas:" + listTask.size)
        }
    }

    fun addSolicitud(email: String, idEvent: String, idList: String, context: Context) {
        isLoading.postValue(true)
        Log.v(logi, "cosas:"+email+"/"+idEvent+"/"+idList)
        viewModelScope.launch {
            val result: SolicitudResponse? = addSolicitudUseCase(email, idEvent, idList)
            isLoading.postValue(false)
            Toast.makeText(context, result!!.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun delList(id:String){
        isLoading.postValue(true)
        isDel.postValue(false)
        viewModelScope.launch {
            val result: DelListResponse? = delListUseCase(id)
            isLoading.postValue(false)
            isDel.postValue(true)
        }
    }
    fun delEvent(id:String){
        isLoading.postValue(true)
        isDel.postValue(false)
        viewModelScope.launch {
            val result: DelListResponse? = delEventUseCase(id)
            isLoading.postValue(false)
            isDel.postValue(true)
        }
    }
}