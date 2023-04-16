package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.LoginResponse
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import javax.inject.Inject

interface LoginRepository {
    suspend fun loginRepository(email: String, password: String, context: Context): Response<LoginResponse>?
}