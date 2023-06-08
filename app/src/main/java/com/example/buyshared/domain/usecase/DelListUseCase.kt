package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.DelListResponse
import com.example.buyshared.domain.repository.remote.DelListasRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DelListUseCase @Inject constructor(
    private val repo:DelListasRepositoryRetrofit
) {
    suspend operator fun invoke(id:String):DelListResponse?{
        return withContext(Dispatchers.IO){
            repo.delListRepository(id)?.body()
        }
    }
}