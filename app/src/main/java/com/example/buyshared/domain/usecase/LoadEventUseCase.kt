package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.EventsResponse
import com.example.buyshared.data.retrofitObjet.EventsResponse2
import com.example.buyshared.domain.repository.remote.LoadEventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoadEventUseCase @Inject constructor(private val loadEventRepository: LoadEventRepository){
    suspend operator fun invoke(userId:String,context: Context): EventsResponse2?{
        return withContext(Dispatchers.IO){
            loadEventRepository.loadEventRepository(userId,context)?.body()
        }
    }
}