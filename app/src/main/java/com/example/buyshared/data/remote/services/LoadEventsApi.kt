package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.EventsResponse2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoadEventsApi {
    @GET("event/user/{id}")
    suspend fun getEventsApiClient(
        @Path("id") id: String,
    ): Response<EventsResponse2>
}