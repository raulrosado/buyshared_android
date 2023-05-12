package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.CompletTaskApi
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.TaskCompletResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TaskRepositoryRetrofitImpl @Inject constructor(
    private val apiRetrofit: CompletTaskApi
):TaskRepositoryRetrofit {
    override suspend fun completTask(idTask: String): Response<TaskCompletResponse>? {
        var response: Response<TaskCompletResponse>? = null
        try {
            response = apiRetrofit.getTaskComplet(idTask)
            Log.v("buysharedLog", "response:"+response.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }
}