package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.LoadAvatarOfListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoadAvatarListApi {
    @GET("list/avatars")
    suspend fun getAvatarsList():Response<LoadAvatarOfListResponse>
}