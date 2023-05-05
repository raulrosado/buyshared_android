package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InserTaskDBUserCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskEntity: TaskEntity) {
        return withContext(Dispatchers.IO) {
            tasksRepository.insert(taskEntity)
        }
    }
}