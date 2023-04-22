package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.data.remote.services.RegisterApiClient
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.RegisterResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiRetrofit: RegisterApiClient
) : RegisterRepository {

    override suspend fun registerRepository(
        name: String,
        email: String,
        password: String,
        context: Context
    ): Response<RegisterResponse>? {
        var response: Response<RegisterResponse>? = null
        try {
            response = apiRetrofit.postRegisterRequest(name,email, password)
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