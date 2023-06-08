package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.DelListResponse
import retrofit2.Response

interface DelListasRepositoryRetrofit {
    suspend fun delListRepository(id:String):Response<DelListResponse>?
}