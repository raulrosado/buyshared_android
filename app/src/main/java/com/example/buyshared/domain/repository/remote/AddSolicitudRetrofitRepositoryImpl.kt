package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.AddSolicitudApiClient
import com.example.buyshared.data.retrofitObjet.SolicitudResponse
import com.example.buyshared.data.retrofitObjet.Task
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AddSolicitudRetrofitRepositoryImpl @Inject constructor(
    private val api:AddSolicitudApiClient
):AddSolicitudRetrofitRepository {
    override suspend fun addSolicitudRetrofitRepository(
        idEvent: String,
        idList: String,
        email: String
    ): Response<SolicitudResponse>? {
        var response: Response<SolicitudResponse>? = null
        try {
            response = api.postLoginRequest(email, idEvent, idList)
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