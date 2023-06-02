package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.Task
import retrofit2.Response
import retrofit2.http.Field

interface AddTaskRepositoryRetrofit {
    suspend fun addTaskRepositoryRetrofit(
        idEvent: String,
        idList: String,
        referencia: String,
        task: String
    ): Response<Task>?
}