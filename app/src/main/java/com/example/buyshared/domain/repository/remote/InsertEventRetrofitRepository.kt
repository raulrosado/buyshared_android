package com.example.buyshared.domain.repository.remote

import android.content.Context
import android.net.Uri
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import retrofit2.Response

interface InsertEventRetrofitRepository {
    suspend fun insertEventRetrofitRepository(
        nombre:String,
        file: Uri,
        context: Context
    ):Response<InsertEventResponse>?
}