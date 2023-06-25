package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.GenericResponse
import com.example.buyshared.data.retrofitObjet.UpdateAvatarResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UpdateAvatarApi {
    @POST("user/postChangePictur")
    @Multipart
    suspend fun changeInfoPersonal(
        @Part file: MultipartBody.Part,
    ): Response<UpdateAvatarResponse>
}

//@Part MultipartBody.Part filePart