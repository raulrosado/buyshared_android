package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.AvatarsRepository
import javax.inject.Inject

class DeleteAvatarByIdEventUseCase @Inject constructor(
    private val repo:AvatarsRepository
){
    operator fun invoke(id:String){
        repo.deleteByIdEvent(id)
    }
}