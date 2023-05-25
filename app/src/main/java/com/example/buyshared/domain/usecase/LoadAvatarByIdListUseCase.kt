package com.example.buyshared.domain.usecase

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.domain.repository.AvatarsRepository
import javax.inject.Inject

class LoadAvatarByIdListUseCase @Inject constructor(
    private val repo:AvatarsRepository
){
    operator fun invoke(id:String): List<Avatar> {
        return repo.getAvatarsByIdList(id)
    }
}