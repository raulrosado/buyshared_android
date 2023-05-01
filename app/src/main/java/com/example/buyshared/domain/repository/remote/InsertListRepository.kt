package com.example.buyshared.domain.repository.remote

import android.content.Context
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import javax.inject.Inject

interface InsertListRepository {
    suspend fun insertListRepository(
        nombre: String,
        estado: String,
        id_event: String,
        referencia: String
    ): Response<InsertListResponse>?
}