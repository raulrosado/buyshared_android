package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.ListsEntity

interface ListsRepository {
    suspend fun insert(lists: ListsEntity)

    suspend fun update(lists: ListsEntity)

    suspend fun delete(lists: ListsEntity)

    suspend fun deleteAll()

    suspend fun getAllLists():List<ListsEntity>

    suspend fun insertAll(listLists:List<ListsEntity>)
}