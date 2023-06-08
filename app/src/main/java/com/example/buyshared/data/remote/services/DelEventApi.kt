package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.DelListResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DelEventApi {
    @DELETE("event/delEvent/{id}")
    suspend fun delEvent(
        @Path("id") id:String
    ): Response<DelListResponse>
}