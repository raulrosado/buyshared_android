package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.TasksRepository
import javax.inject.Inject

class CompletTaskDBUserCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    operator fun invoke(id:String, estado: Int){
        tasksRepository.completTask(id,estado)
    }
}