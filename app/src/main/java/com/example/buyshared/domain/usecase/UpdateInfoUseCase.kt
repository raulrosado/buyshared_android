package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateInfoUseCase @Inject constructor(
    private val rep:ChangeInfoPersonalRetrofitRepository
) {
    suspend operator fun invoke(email:String,name:String){
        return withContext(Dispatchers.IO){
            rep.updateInfoPersonal(email,name)?.body()
        }
    }
}