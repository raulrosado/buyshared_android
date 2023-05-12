package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.TaskCompletResponse
import com.example.buyshared.domain.repository.TasksRepository
import com.example.buyshared.domain.repository.remote.TaskRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompletTaskUseCase @Inject constructor(
    private val tasksRepository: TaskRepositoryRetrofit
) {
    suspend operator fun invoke(id:String):TaskCompletResponse?{
        return withContext(Dispatchers.IO){
            tasksRepository.completTask(id)?.body()
        }
    }
}