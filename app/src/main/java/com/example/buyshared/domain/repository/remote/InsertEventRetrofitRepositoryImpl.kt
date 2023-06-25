package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.InsertEventApiClient
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.IOException
import javax.inject.Inject


class InsertEventRetrofitRepositoryImpl @Inject constructor(
    private val apiRetrofit: InsertEventApiClient
) : InsertEventRetrofitRepository {
    override suspend fun insertEventRetrofitRepository(
        nombre: String,
        file: File
    ): Response<InsertEventResponse>? {
        var response: Response<InsertEventResponse>? = null
        try {
//            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//            val fullName = RequestBody.create(MediaType.parse("multipart/form-data"), nombre)
//
//            Log.v("buysharedLog", "fullName:" + fullName.toString())
//            response = apiRetrofit.postInsertEvent(requestFile, nombre)

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