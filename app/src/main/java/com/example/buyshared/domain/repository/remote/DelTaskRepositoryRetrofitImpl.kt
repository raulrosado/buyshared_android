package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.DelTaskApiClient
import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import com.example.buyshared.data.retrofitObjet.EventsResponse2
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DelTaskRepositoryRetrofitImpl @Inject constructor(
    private val apiRetrofit:DelTaskApiClient
):DelTaskRepositoryRetrofit{
    override suspend fun delTaskRepository(id: String): Response<DelTaskResponse>? {
        var response: Response<DelTaskResponse>? = null
        try {
            response = apiRetrofit.delTask(id)
            Log.v("buysharedLog", "response:"+response.toString())
        } catch (e: HttpException) {
//            response= LoginResponse()
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }
}