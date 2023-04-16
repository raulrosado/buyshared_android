package com.example.buyshared.ui.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.domain.repository.UserRepository
import com.example.buyshared.domain.usecase.LoginUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    private val userRepository: UserRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isLogin = MutableLiveData<Boolean>()
    val isRegister = MutableLiveData<Boolean>()
    val loginResponse = MutableLiveData<LoginResponse>()
//    val registroResponse = MutableLiveData<RegistroResponse>()
//    val olvidoResponse = MutableLiveData<OlvidoResponse>()
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"

    fun login(email:String,password:String,context:Context){
        isLoading.postValue(true)
        tinyDB = TinyDB(context)
        viewModelScope.launch {
            val result: LoginResponse? = loginUseCase(email, password, context)
            Log.v(logi, "success:" + result)
            if ((result != null)||(result?.status.toString() != "error")){
                Log.v(logi, "success:" + result)
                isLogin.postValue(true)
                isLoading.postValue(false)

                tinyDB.putString("token", result?.token.toString())
                tinyDB.putObject("user", result!!.user)

//                usuarioRepository.insert(result?.user.toUsuario())
            } else {
                isLoading.postValue(false)
                isLogin.postValue(false)
            }
        }
    }

    fun registro(name: String, email: String, password: String, context: Context) {
        
    }


}
