package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.EventDetailResponse
import com.example.buyshared.domain.repository.remote.EventsRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadEventDetailUseCase @Inject constructor(
    private val eventsRepositoryRetrofit: EventsRepositoryRetrofit
) {
    suspend operator fun invoke(id:String):EventDetailResponse?{
        return withContext(Dispatchers.IO){
            eventsRepositoryRetrofit.loadEventDetailRetrofit(id)?.body()
        }
    }
}