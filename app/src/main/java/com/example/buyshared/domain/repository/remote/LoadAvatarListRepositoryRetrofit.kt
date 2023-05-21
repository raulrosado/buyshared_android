package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.LoadAvatarOfListResponse
import retrofit2.Response

interface LoadAvatarListRepositoryRetrofit {
    suspend fun loadAvatarsRetrofit():Response<LoadAvatarOfListResponse>?
}