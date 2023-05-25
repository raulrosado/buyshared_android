package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.LoadEventsApi
import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.EventsResponse2
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoadEventRepositoryImpl  @Inject constructor(
    private val apiRetrofit: LoadEventsApi
) : LoadEventRepository {
    override suspend fun loadEventRepository(
        userId: String,
        context: Context
    ): Response<EventsResponse2>? {
        var response: Response<EventsResponse2>? = null
        try {
            response = apiRetrofit.getEventsApiClient(userId)
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