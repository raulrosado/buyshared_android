package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.domain.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertDBUseCase @Inject constructor(
        private val insertEventRepository: EventsRepository
    ){
    suspend operator fun invoke(event:EventsEntity){
        return withContext(Dispatchers.IO){
            insertEventRepository.insert(event)
        }
    }
}