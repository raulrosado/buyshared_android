package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DelTasksDBByIdListUserCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(id:String){
        return withContext(Dispatchers.IO) {
            tasksRepository.delTasksByIdList(id)
        }
    }
}