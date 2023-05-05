package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.LoadListDetailResponse
import com.example.buyshared.domain.repository.remote.LoadListsRepository
import com.example.buyshared.domain.repository.remote.LoadListsTasksRepository
import javax.inject.Inject

class LoadListTaskRetrofitUserCase @Inject constructor(
    private val loadListsTasksRepository: LoadListsTasksRepository
) {
    suspend operator fun invoke(idList:String):LoadListDetailResponse?{
        return loadListsTasksRepository.loadListsTasksRepository(idList)?.body()
    }
}