package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.InsertTaskApiClient
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.Task
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AddTaskRepositoryRetrofitImpl @Inject constructor(
    private val apiRetrofit: InsertTaskApiClient
):AddTaskRepositoryRetrofit {
    override suspend fun addTaskRepositoryRetrofit(
        idEvent: String,
        idList: String,
        referencia: String,
        task: String
    ): Response<Task>? {
        var response: Response<Task>? = null
        try {
            response = apiRetrofit.postInsertList(idEvent,idList,referencia, task)
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