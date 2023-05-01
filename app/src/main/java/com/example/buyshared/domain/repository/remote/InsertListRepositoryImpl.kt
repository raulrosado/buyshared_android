package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.InsertListApiClient
import com.example.buyshared.data.remote.services.LoadEventsApi
import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class InsertListRepositoryImpl  @Inject constructor(
    private val apiRetrofit: InsertListApiClient
) : InsertListRepository {

    override suspend fun insertListRepository(
        nombre: String,
        estado: String,
        id_event: String,
        referencia: String
    ): Response<InsertListResponse>? {
        var response: Response<InsertListResponse>? = null
        try {
            response = apiRetrofit.postInsertList(nombre, estado, id_event, referencia)
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