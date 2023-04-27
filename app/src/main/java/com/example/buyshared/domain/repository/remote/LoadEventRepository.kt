package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.EventResponse
import retrofit2.Response

interface LoadEventRepository {
    suspend fun loadEventRepository(userId:String,context: Context):Response<EventResponse>?
}