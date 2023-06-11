package com.example.buyshared.domain.usecase

import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(
    private val rep:ChangeInfoPersonalRetrofitRepository
) {
    suspend operator fun invoke(antiguaPass:String,newPass:String,repetPassw:String){
        return withContext(Dispatchers.IO){
            rep.updatePassword(antiguaPass,newPass,repetPassw)?.body()
        }
    }
}