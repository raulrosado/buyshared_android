package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.domain.repository.AvatarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertAvatarsDBUseCase @Inject constructor(
    private val avatarsRepository: AvatarsRepository
) {
    operator fun invoke(avatarsEntity: AvatarsEntity){
        return avatarsRepository.insert(avatarsEntity)
    }
}