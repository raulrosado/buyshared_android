package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import com.example.buyshared.domain.repository.remote.InsertEventRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class InsetEventRetrofitUseCase @Inject constructor(private val insertEventRetrofitRepository: InsertEventRetrofitRepository) {
    suspend operator fun invoke(nombre: String, file: File): InsertEventResponse? {
        return withContext(Dispatchers.IO) {
            insertEventRetrofitRepository.insertEventRetrofitRepository(nombre, file)?.body()
        }
    }
}