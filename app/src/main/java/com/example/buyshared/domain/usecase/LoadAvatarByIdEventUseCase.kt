package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.domain.repository.AvatarsRepository
import javax.inject.Inject

class LoadAvatarByIdEventUseCase @Inject constructor(
    private val avatarsRepository: AvatarsRepository
){
    operator fun invoke(id:String): List<Avatar> {
        return avatarsRepository.getAvatarsByIdEvent(id)
    }
}