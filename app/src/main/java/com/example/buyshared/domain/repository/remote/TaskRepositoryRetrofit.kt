package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.TaskCompletResponse
import retrofit2.Response

interface TaskRepositoryRetrofit {
    suspend fun completTask(idTask:String):Response<TaskCompletResponse>?
}