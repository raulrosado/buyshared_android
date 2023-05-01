package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.ListsResponse
import com.example.buyshared.domain.repository.remote.LoadListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoadListsUseCase @Inject constructor(private val loadListsRepository: LoadListsRepository){
    suspend operator fun invoke(userId:String,context: Context): ListsResponse?{
        return withContext(Dispatchers.IO){
            loadListsRepository.loadListsRepository(userId,context)?.body()
        }
    }
}