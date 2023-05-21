package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.domain.repository.AvatarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadAvatarByIdList @Inject constructor(
    private val repo:AvatarsRepository
){
    operator fun invoke(id:String): List<AvatarsEntity> {
        return repo.getAvatarsByIdList(id)

    }
}