package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.Task
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InsertTaskApiClient {
    @FormUrlEncoded
    @POST("task/addTask")
    suspend fun postInsertList(
        @Field("idEvent") idEvent: String,
        @Field("idList") idList: String,
        @Field("referencia") referencia: String,
        @Field("task") task: String
    ): Response<Task>
}