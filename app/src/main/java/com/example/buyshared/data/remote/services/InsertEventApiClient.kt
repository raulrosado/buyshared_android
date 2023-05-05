package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface InsertEventApiClient {
    @POST("event/addEvent")
    @Multipart
    suspend fun postInsertEvent(
        @Part("file") file: RequestBody,
        @Part("name_list",encoding = "8-bit") nombre: String
    ): Response<InsertEventResponse>
}