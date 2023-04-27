package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.EventResponse
import com.example.buyshared.data.retrofitObjet.ListResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LoadListsApi {
    @GET("list/user/{id}")
    suspend fun getListsApiClient(
        @Path("id") id: String,
    ): Response<ListResponse>
}