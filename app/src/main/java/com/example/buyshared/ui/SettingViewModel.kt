package com.example.buyshared.ui

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyshared.domain.usecase.UpdateAvatarUseCase
import com.example.buyshared.domain.usecase.UpdateInfoUseCase
import com.example.buyshared.domain.usecase.UpdatePasswordUseCase
import com.example.buyshared.ui.Activity.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val updateAvatarUseCase: UpdateAvatarUseCase,
    private val updateInfoUseCase: UpdateInfoUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    lateinit var tinyDB: TinyDB
    var logi = "buysharedLog"

    fun updateInfoPersonal(email:String,name:String){
        isLoading.postValue(true)
        viewModelScope.launch {
            updateInfoUseCase(email, name)
            isLoading.postValue(false)
        }
    }
    fun updatePassword(antiguaPass:String,newPass:String,repetPassw:String){
        isLoading.postValue(true)
        viewModelScope.launch {
            updatePasswordUseCase(antiguaPass, newPass, repetPassw)
            isLoading.postValue(false)
        }
    }
    fun updateAvatar(uriFile: Uri,context: Activity){
        isLoading.postValue(true)
        viewModelScope.launch {
            updateAvatarUseCase(uriFile, context)
            isLoading.postValue(false)
        }
    }
}