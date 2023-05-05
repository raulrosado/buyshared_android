package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.domain.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEventByIdUserCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {
     operator fun invoke(id:String):EventsEntity?{
        return eventsRepository.getById(id)
    }
}