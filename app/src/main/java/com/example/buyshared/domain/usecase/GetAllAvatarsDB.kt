package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.domain.repository.AvatarsRepository
import javax.inject.Inject

class GetAllAvatarsDB @Inject constructor(
    private val avatarsRepository: AvatarsRepository
) {
    suspend operator fun invoke():List<AvatarsEntity>{
       return avatarsRepository.getAll()
    }
}