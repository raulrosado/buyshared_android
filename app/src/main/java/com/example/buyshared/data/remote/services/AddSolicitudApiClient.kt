package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.SolicitudResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AddSolicitudApiClient {
    @FormUrlEncoded
    @POST("solicitudes/sendSolicitud")
    suspend fun postLoginRequest(
        @Field("email") email: String,
        @Field("idEvent") idEvent: String,
        @Field("idList") idList: String
    ): Response<SolicitudResponse>
}