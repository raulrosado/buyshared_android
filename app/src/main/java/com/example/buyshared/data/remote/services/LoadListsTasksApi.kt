package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoadListsTasksApi {
    @GET("list/{id}")
    suspend fun loadListsTasksApi(
        @Path("id") id: String,
    ): Response<LoadListDetailResponse>
}