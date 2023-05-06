package com.example.buyshared.domain.repository

import android.util.Log
import com.example.buyshared.data.dao.EventsDao
import com.example.buyshared.data.dao.ListasDao
import com.example.buyshared.data.dao.TaskDao
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val dao: TaskDao
):TasksRepository {
    override suspend fun insert(task: TaskEntity) {
        dao.insert(task)
    }

    override suspend fun update(task: TaskEntity) {
        dao.update(task)
    }

    override suspend fun delete(task: TaskEntity) {
        dao.delete(task)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getAllTasks(): List<TaskEntity> {
        return dao.getAllTasks()
    }

    override fun getByIdList(id: String): List<TaskEntity> {
        return dao.getTasksByIdList(id)
    }

    override fun delTasksByIdList(id: String) {
        dao.delTasksByIdList(id)
    }


}