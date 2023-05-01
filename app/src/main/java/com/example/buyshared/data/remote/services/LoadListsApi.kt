package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.ListsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoadListsApi {
    @GET("list/user/{id}")
    suspend fun getListsApiClient(
        @Path("id") id: String,
    ): Response<ListsResponse>
}