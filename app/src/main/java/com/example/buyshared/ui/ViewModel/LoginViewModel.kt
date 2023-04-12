package com.example.buyshared.ui.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyshared.ui.Activity.TinyDB

class LoginViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isLogin = MutableLiveData<Boolean>()
    val isRegister = MutableLiveData<Boolean>()
//    val loginResponse = MutableLiveData<LoginResponse>()
//    val registroResponse = MutableLiveData<RegistroResponse>()
//    val olvidoResponse = MutableLiveData<OlvidoResponse>()
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"

    fun login(txtEmail:String,txtPassword:String,context:Context){

    }

}