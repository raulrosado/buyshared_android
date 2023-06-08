package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.DelListResponse
import retrofit2.Response

interface DelEventRepositoryRetrofit {
    suspend fun delEventRepository(id:String): Response<DelListResponse>?
}