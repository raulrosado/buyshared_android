package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.domain.repository.TasksRepository
import javax.inject.Inject

class DelTaskDBUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
){
    suspend operator fun invoke(id:String){
        tasksRepository.delTasksById(id)
    }
}