package com.example.buyshared.ui.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.data.retrofitObjet.EventResponse
import com.example.buyshared.data.retrofitObjet.ListResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.User
import com.example.buyshared.domain.repository.UserRepository
import com.example.buyshared.domain.usecase.LoadEventUseCase
import com.example.buyshared.domain.usecase.LoadListsUseCase
import com.example.buyshared.domain.usecase.LoginUseCase
import com.example.buyshared.domain.usecase.RegisterUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadEventUseCase: LoadEventUseCase,
    private val loadListsUseCase: LoadListsUseCase
): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"
    val isLoadEvent = MutableLiveData<Boolean>()

    fun loadEventos(context:Context){
        isLoading.postValue(true)
        tinyDB = TinyDB(context)

        val user = tinyDB.getObject("user",User::class.java)

        Log.v(logi, "user--"+ user)
        Log.v(logi, "user--"+ user._id)

        viewModelScope.launch {
            val result: EventResponse? = loadEventUseCase(user._id, context)
            Log.v(logi, "success:" + result)
        }
    }

    fun loadLists(context:Context){
        isLoading.postValue(true)
        tinyDB = TinyDB(context)

        val user = tinyDB.getObject("user",User::class.java)

        Log.v(logi, "user--"+ user)
        Log.v(logi, "user--"+ user._id)

        viewModelScope.launch {
            val result: ListResponse? = loadListsUseCase(user._id, context)
            Log.v(logi, "success:" + result)
        }
    }


}