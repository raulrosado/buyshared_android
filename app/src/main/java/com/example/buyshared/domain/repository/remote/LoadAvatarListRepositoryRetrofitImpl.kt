package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.LoadAvatarListApi
import com.example.buyshared.data.retrofitObjet.LoadAvatarOfListResponse
import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoadAvatarListRepositoryRetrofitImpl @Inject constructor(
    private val apiRetrofit:LoadAvatarListApi
):LoadAvatarListRepositoryRetrofit{
    override suspend fun loadAvatarsRetrofit(): Response<LoadAvatarOfListResponse>? {
        var response: Response<LoadAvatarOfListResponse>? = null
        try {
            response = apiRetrofit.getAvatarsList()
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