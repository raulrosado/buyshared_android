package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.Task
import com.example.buyshared.domain.repository.remote.AddTaskRepositoryRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertTaskRetrofitUseCase @Inject constructor(
    private val addTaskRetrofit: AddTaskRepositoryRetrofit
) {
    suspend operator fun invoke(
        idEvent: String,
        idList: String,
        referencia: String,
        task: String
    ): Task? {
        return withContext(Dispatchers.IO) {
            addTaskRetrofit.addTaskRepositoryRetrofit(idEvent, idList, referencia, task)?.body()
        }
    }
}