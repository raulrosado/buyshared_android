package com.example.buyshared.domain.repository.remote

import android.app.Activity
import android.content.Context
import android.net.Uri
import com.example.buyshared.data.retrofitObjet.GenericResponse
import com.example.buyshared.data.retrofitObjet.UpdateAvatarResponse
import retrofit2.Response

interface ChangeInfoPersonalRetrofitRepository {
    suspend fun updateInfoPersonal(
        email:String,
        name:String
    ):Response<GenericResponse>?

    suspend fun updateAvatar(
        uriFile: Uri,context:Activity
    ):Response<UpdateAvatarResponse>?

    suspend fun updatePassword(
        passwordAnt: String,
        passwordNew: String,
        passwordRepetNew: String
    ):Response<GenericResponse>?

}