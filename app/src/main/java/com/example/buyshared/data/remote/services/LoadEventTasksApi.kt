package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.EventDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoadEventTasksApi {
    @GET("event/{id}")
    suspend fun loadEventTasksApi(
        @Path("id") id: String,
    ): Response<EventDetailResponse>
}