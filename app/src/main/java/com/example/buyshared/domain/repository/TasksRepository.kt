package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.TaskEntity

interface TasksRepository {
    suspend fun insert(task:TaskEntity)

    suspend fun update(task:TaskEntity)

    suspend fun delete(task:TaskEntity)
    suspend fun delTasksById(id:String)

    suspend fun deleteAll()

    suspend fun getAllTasks():List<TaskEntity>

    fun getByIdList(id:String):List<TaskEntity>
    fun getByIdEvent(id:String):List<TaskEntity>

    fun delTasksByIdList(id:String)

    fun delTaskByIdEvent(id:String)

    fun completTask(id: String, estado: Int)
}