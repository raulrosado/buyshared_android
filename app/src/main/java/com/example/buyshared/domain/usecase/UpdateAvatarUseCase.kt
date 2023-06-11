package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class UpdateAvatarUseCase @Inject constructor(
    private val rep:ChangeInfoPersonalRetrofitRepository
) {
    suspend operator fun invoke(file: File){
        return withContext(Dispatchers.IO){
            rep.updateAvatar(file)?.body()
        }
    }
}