package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.LoadListsApi
import com.example.buyshared.data.remote.services.LoadListsTasksApi
import com.example.buyshared.data.retrofitObjet.ListsResponse
import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoadListsTasksRepositoryImpl  @Inject constructor(
    private val apiRetrofit: LoadListsTasksApi
) : LoadListsTasksRepository {

    override suspend fun loadListsTasksRepository(idList: String): Response<LoadListDetailResponse>? {
        var response: Response<LoadListDetailResponse>? = null
        try {
            response = apiRetrofit.loadListsTasksApi(idList)
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