package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.RegisterResponse
import com.example.buyshared.domain.repository.remote.LoginRepository
import com.example.buyshared.domain.repository.remote.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository){
    suspend operator fun invoke(name:String, email: String, password: String, context: Context): RegisterResponse? {
        return withContext(Dispatchers.IO) {
            registerRepository.registerRepository(name,email, password, context)?.body()
        }
    }
}