package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.GenericResponse
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import com.example.buyshared.data.retrofitObjet.UpdateAvatarResponse
import retrofit2.Response
import retrofit2.http.Field
import java.io.File

interface ChangeInfoPersonalRetrofitRepository {
    suspend fun updateInfoPersonal(
        email:String,
        name:String
    ):Response<GenericResponse>?

    suspend fun updateAvatar(
        file: File
    ):Response<UpdateAvatarResponse>?

    suspend fun updatePassword(
        passwordAnt: String,
        passwordNew: String,
        passwordRepetNew: String
    ):Response<GenericResponse>?
}