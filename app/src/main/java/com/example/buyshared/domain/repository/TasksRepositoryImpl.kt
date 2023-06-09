package com.example.buyshared.domain.repository

import com.example.buyshared.data.dao.TaskDao
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

    override suspend fun delTasksById(id: String) {
        dao.delTasksById(id)
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

    override fun getByIdEvent(id: String): List<TaskEntity> {
        return dao.getTasksByIdEvent(id)
    }

    override fun delTasksByIdList(id: String) {
        dao.delTasksByIdList(id)
    }

    override fun delTaskByIdEvent(id: String) {
        dao.delTasksByIdEvent(id)
    }

    override fun completTask(id: String, estado: Int) {
        dao.completTask(id,estado)
    }
}