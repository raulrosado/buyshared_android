package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.InsertListResponse
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.domain.repository.remote.InsertListRepository
import com.example.buyshared.domain.repository.remote.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertListRetrofitUseCase @Inject constructor(private val insertListRepository: InsertListRepository){
    suspend operator fun invoke( nombre: String,
                                 estado: String,
                                 id_event: String,
                                 referencia: String): InsertListResponse? {
        return withContext(Dispatchers.IO) {
            insertListRepository.insertListRepository(nombre, estado,id_event,referencia)?.body()
        }
    }
}