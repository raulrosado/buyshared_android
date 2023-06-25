package com.example.buyshared.domain.usecase

import android.app.Activity
import android.content.Context
import android.net.Uri
import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateAvatarUseCase @Inject constructor(
    private val rep:ChangeInfoPersonalRetrofitRepository
) {
    suspend operator fun invoke(uriFile: Uri,context: Activity){
        return withContext(Dispatchers.IO){
            rep.updateAvatar(uriFile, context)?.body()
        }
    }
}