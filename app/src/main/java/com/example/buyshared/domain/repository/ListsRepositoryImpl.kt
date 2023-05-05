package com.example.buyshared.domain.repository

import android.util.Log
import com.example.buyshared.data.dao.EventsDao
import com.example.buyshared.data.dao.ListasDao
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import javax.inject.Inject

class ListsRepositoryImpl @Inject constructor(
    private val dao: ListasDao
):ListsRepository {
    override suspend fun insert(list: ListsEntity) {
        Log.v("buysharedLog","insert list"+ list.toString())
        dao.insert(list)
    }

    override suspend fun update(lists: ListsEntity) {
        dao.update(lists)
    }

    override suspend fun delete(lists: ListsEntity) {
        dao.delete(lists)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getAllLists(): List<ListsEntity> {
       return dao.getAll()
    }

    override suspend fun insertAll(listLists: List<ListsEntity>) {
        dao.insertAll(listLists)
    }

    override fun getById(id: String): ListsEntity {
        return dao.getById(id)
    }

}