package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CleanEventsDB @Inject constructor(
    private val eventsRepository: EventsRepository
) {
    suspend operator fun invoke(){
        return withContext(Dispatchers.IO){
            eventsRepository.deleteAll()
        }
    }
}