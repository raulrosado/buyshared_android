package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.LoadListsApi
import com.example.buyshared.data.retrofitObjet.ListsResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoadListsRepositoryImpl  @Inject constructor(
    private val apiRetrofit: LoadListsApi
) : LoadListsRepository {
    override suspend fun loadListsRepository(
        userId: String,
        context: Context
    ): Response<ListsResponse>? {
        var response: Response<ListsResponse>? = null
        try {
            response = apiRetrofit.getListsApiClient(userId)
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