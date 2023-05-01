package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InsertListApiClient {
    @FormUrlEncoded
    @POST("list/addList")
    suspend fun postInsertList(
        @Field("nombre") nombre: String,
        @Field("estado") estado: String,
        @Field("id_event") id_event: String,
        @Field("referencia") referencia: String
    ): Response<InsertListResponse>
}