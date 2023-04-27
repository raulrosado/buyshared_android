package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.EventResponse
import com.example.buyshared.domain.repository.remote.LoadEventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoadEventUseCase @Inject constructor(private val loadEventRepository: LoadEventRepository){
    suspend operator fun invoke(userId:String,context: Context):EventResponse?{
        return withContext(Dispatchers.IO){
            loadEventRepository.loadEventRepository(userId,context)?.body()
        }
    }
}