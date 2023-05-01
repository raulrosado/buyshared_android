package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.domain.repository.EventsRepository
import com.example.buyshared.domain.repository.ListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertListsDBUseCase @Inject constructor(
        private val insertListsRepository: ListsRepository
    ){
    suspend operator fun invoke(list:ListsEntity){
        return withContext(Dispatchers.IO){
            insertListsRepository.insert(list)
        }
    }
}