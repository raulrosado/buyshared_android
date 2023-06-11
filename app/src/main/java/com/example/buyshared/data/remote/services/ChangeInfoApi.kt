package com.example.buyshared.data.remote.services

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.buyshared.data.retrofitObjet.GenericResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ChangeInfoApi {
    @FormUrlEncoded
    @POST("user/postChangeInfo")
    suspend fun changeInfoPersonal(
        @Field("email") email: String,
        @Field("name") name:String
    ):Response<GenericResponse>
}