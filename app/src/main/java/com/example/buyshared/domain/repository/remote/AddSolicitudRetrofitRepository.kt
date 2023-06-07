package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.SolicitudResponse
import com.example.buyshared.data.retrofitObjet.Task
import retrofit2.Response

interface AddSolicitudRetrofitRepository {
    suspend fun addSolicitudRetrofitRepository(
        idEvent: String,
        idList: String,
        email: String
    ): Response<SolicitudResponse>?
}