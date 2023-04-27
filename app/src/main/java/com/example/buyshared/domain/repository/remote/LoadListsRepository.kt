package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.EventResponse
import com.example.buyshared.data.retrofitObjet.ListResponse
import retrofit2.Response

interface LoadListsRepository {
    suspend fun loadListsRepository(userId:String,context: Context):Response<ListResponse>?
}