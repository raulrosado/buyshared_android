package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.DelTaskResponse
import com.example.buyshared.domain.repository.remote.DelTaskRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DelTaskRetrofitUseCase @Inject constructor(
    private val rep:DelTaskRepositoryRetrofit
) {
    suspend operator fun invoke(id:String):DelTaskResponse?{
        return withContext(Dispatchers.IO) {
            rep.delTaskRepository(id)?.body()
        }
    }
}