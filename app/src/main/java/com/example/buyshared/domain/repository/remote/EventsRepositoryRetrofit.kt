package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.EventDetailResponse
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import retrofit2.Response
import java.io.File

interface EventsRepositoryRetrofit {
    suspend fun loadEventDetailRetrofit(
        idEvent:String
    ): Response<EventDetailResponse>?
}