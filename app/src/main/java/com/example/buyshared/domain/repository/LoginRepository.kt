package com.example.buyshared.domain.repository

import android.content.Context
import com.example.buyshared.data.retrofitObjet.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun loginRepository(email: String, password: String, context: Context): Response<LoginResponse>?
}