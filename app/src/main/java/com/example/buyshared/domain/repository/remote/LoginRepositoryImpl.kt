package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.util.Log
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.data.retrofitObjet.LoginResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiRetrofit: LoginApiClient
) : LoginRepository {

    override suspend fun loginRepository(
        email: String,
        password: String,
        context: Context
    ): Response<LoginResponse>? {
        var response: Response<LoginResponse>? = null
        try {
            response = apiRetrofit.postLoginRequest(email, password)
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