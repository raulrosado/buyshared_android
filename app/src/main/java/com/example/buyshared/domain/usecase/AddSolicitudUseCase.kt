package com.example.buyshared.domain.usecase

import com.example.buyshared.data.retrofitObjet.SolicitudResponse
import com.example.buyshared.domain.repository.remote.AddSolicitudRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddSolicitudUseCase @Inject constructor(
    private val rep:AddSolicitudRetrofitRepository
) {
    suspend operator fun invoke(email:String,idEvent:String,idList:String):SolicitudResponse?{
        return withContext(Dispatchers.IO){
            rep.addSolicitudRetrofitRepository(idEvent, idList, email)?.body()
        }
    }
}