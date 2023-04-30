package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.EventsEntity

interface EventsRepository {
    suspend fun insert(events: EventsEntity)

    suspend fun update(events: EventsEntity)

    suspend fun delete(events: EventsEntity)

    suspend fun deleteAll()

    suspend fun getAllEvents():List<EventsEntity>

    suspend fun insertAll(listEvents:List<EventsEntity>)
}