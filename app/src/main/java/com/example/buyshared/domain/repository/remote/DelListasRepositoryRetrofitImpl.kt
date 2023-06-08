package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.DelEventApi
import com.example.buyshared.data.remote.services.DelListApi
import com.example.buyshared.data.retrofitObjet.DelListResponse
import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DelListasRepositoryRetrofitImpl @Inject constructor(
    private val apiDelClientList:DelListApi
):DelListasRepositoryRetrofit{
    override suspend fun delListRepository(id: String): Response<DelListResponse>? {
        var response: Response<DelListResponse>? = null
        try {
            response = apiDelClientList.delList(id)
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