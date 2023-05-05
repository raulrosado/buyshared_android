package com.example.buyshared.domain.repository.remote

import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import retrofit2.Response
import java.io.File

interface InsertEventRetrofitRepository {
    suspend fun insertEventRetrofitRepository(
        nombre:String,
        file:File
    ):Response<InsertEventResponse>?
}