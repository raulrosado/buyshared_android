package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.LoadEventTasksApi
import com.example.buyshared.data.retrofitObjet.EventDetailResponse
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class EventsRepositoryRetrofitImpl @Inject constructor(
    private val loadEventTasksApi: LoadEventTasksApi
): EventsRepositoryRetrofit {
    override suspend fun loadEventDetailRetrofit(idEvent: String): Response<EventDetailResponse>? {
        var response: Response<EventDetailResponse>? = null
        try {
            response = loadEventTasksApi.loadEventTasksApi(idEvent)
            Log.v("buysharedLog", "response:" + response.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }
}