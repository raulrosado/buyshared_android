package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DelTaskApiClient {
    @DELETE("task/delTask/{id}")
    suspend fun delTask(
        @Path("id") id:String
    ):Response<DelTaskResponse>
}