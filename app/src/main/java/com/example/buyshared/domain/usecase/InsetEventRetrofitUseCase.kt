package com.example.buyshared.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.buyshared.data.retrofitObjet.InsertEventResponse
import com.example.buyshared.domain.repository.remote.InsertEventRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsetEventRetrofitUseCase @Inject constructor(private val insertEventRetrofitRepository: InsertEventRetrofitRepository) {
    suspend operator fun invoke(nombre: String, file: Uri,context: Context): InsertEventResponse? {
        return withContext(Dispatchers.IO) {
            insertEventRetrofitRepository.insertEventRetrofitRepository(nombre, file, context)?.body()
        }
    }
}