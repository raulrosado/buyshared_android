package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.ListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CleanListsDBUseCase @Inject constructor(
    private val listsRepository: ListsRepository
) {
    suspend operator fun invoke(){
        return withContext(Dispatchers.IO){
            listsRepository.deleteAll()
        }
    }
}