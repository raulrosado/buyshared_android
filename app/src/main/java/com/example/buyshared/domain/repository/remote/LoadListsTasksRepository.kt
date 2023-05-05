package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.ListsResponse
import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import retrofit2.Response

interface LoadListsTasksRepository {
    suspend fun loadListsTasksRepository(idList:String):Response<LoadListDetailResponse>?
}