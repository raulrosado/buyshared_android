package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.DelListResponse
import com.example.buyshared.domain.repository.remote.DelEventRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.DelListasRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DelEventUseCase @Inject constructor(
    private val repo:DelEventRepositoryRetrofit
) {
    suspend operator fun invoke(id:String):DelListResponse?{
        return withContext(Dispatchers.IO){
            repo.delEventRepository(id)?.body()
        }
    }
}