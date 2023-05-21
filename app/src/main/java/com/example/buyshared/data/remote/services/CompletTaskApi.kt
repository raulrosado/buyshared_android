package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.TaskCompletResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CompletTaskApi {
    @GET("task/complet/{id}")
    suspend fun getTaskComplet(
        @Path("id") id: String
    ): Response<TaskCompletResponse>
}