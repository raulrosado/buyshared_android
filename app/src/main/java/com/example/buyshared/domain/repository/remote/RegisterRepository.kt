package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.RegisterResponse
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import javax.inject.Inject

interface RegisterRepository {
    suspend fun registerRepository(name: String,email: String, password: String, context: Context): Response<RegisterResponse>?
}