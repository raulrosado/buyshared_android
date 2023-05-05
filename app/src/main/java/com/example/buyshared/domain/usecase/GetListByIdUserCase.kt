package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.domain.repository.ListsRepository
import com.example.buyshared.domain.repository.remote.InsertListRepository
import javax.inject.Inject

class GetListByIdUserCase @Inject constructor(
    private val listRepository: ListsRepository
) {
    operator fun invoke(id:String): ListsEntity?{
        return listRepository.getById(id)
    }
}