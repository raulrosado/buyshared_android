package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.LoadAvatarOfListResponse
import com.example.buyshared.domain.repository.remote.LoadAvatarListRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadAvatarsListRetrofitUseCase @Inject constructor(
    private val loadAvatarListRepositoryRetrofit: LoadAvatarListRepositoryRetrofit
){
    suspend operator fun invoke():LoadAvatarOfListResponse?{
        return withContext(Dispatchers.IO){
            loadAvatarListRepositoryRetrofit.loadAvatarsRetrofit()?.body()
        }
    }
}