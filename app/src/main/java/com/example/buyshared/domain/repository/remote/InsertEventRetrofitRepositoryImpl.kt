package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.buyshared.core.Function
import com.example.buyshared.data.remote.services.InsertEventApiClient
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
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
        uriFile: Uri,
        context: Context
    ): Response<InsertEventResponse>? {
        var response: Response<InsertEventResponse>? = null
        try {
            val function = Function()
            val file = File(function.getRealPathFromURI(context!!, uriFile!!))
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val nombreRequest = RequestBody.create("text/plain".toMediaTypeOrNull(), nombre.toString());
            val part = MultipartBody.Part.createFormData("file", file.name, requestBody)

            response = apiRetrofit.postInsertEvent(part, nombreRequest)
            Log.v("buysharedLog", "response Api:" + response.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }
}