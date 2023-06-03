package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import retrofit2.Response

interface DelTaskRepositoryRetrofit {
    suspend fun delTaskRepository(id:String):Response<DelTaskResponse>?
}