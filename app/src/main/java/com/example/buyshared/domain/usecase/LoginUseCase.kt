package com.example.buyshared.domain.usecase

import android.content.Context
import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.domain.repository.remote.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository){
    suspend operator fun invoke(email: String, password: String, context: Context): LoginResponse? {
        return withContext(Dispatchers.IO) {
            loginRepository.loginRepository(email, password, context)?.body()
        }
    }
}