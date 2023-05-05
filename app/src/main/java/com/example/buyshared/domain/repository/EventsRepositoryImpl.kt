package com.example.buyshared.domain.repository

import android.util.Log
import com.example.buyshared.data.dao.EventsDao
import com.example.buyshared.data.model.EventsEntity
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val dao: EventsDao
):EventsRepository {
    override suspend fun insert(events: EventsEntity) {
        Log.v("buysharedLog","insert evento:"+events.nombre)
        dao.insert(events)
    }

    override suspend fun update(events: EventsEntity) {
        dao.update(events)
    }

    override suspend fun delete(events: EventsEntity) {
        dao.delete(events)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun insertAll(listEvents: List<EventsEntity>) {
        dao.insertAll(listEvents)
    }

    override fun getById(id: String): EventsEntity {
        Log.v("buyshared","id seleccionado:"+id)
        return dao.getById(id)
    }

    override suspend fun getAllEvents(): List<EventsEntity> {
        return dao.getAll()
    }

}