package com.example.buyshared.domain.repository.remote

import android.util.Log
import com.example.buyshared.data.remote.services.ChangeInfoApi
import com.example.buyshared.data.remote.services.UpdateAvatarApi
import com.example.buyshared.data.remote.services.UpdatePasswordApi
import com.example.buyshared.data.retrofitObjet.DelListResponse
import com.example.buyshared.data.retrofitObjet.GenericResponse
import com.example.buyshared.data.retrofitObjet.UpdateAvatarResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ChangeInfoPersonalRetrofitRepositoryImpl @Inject constructor(
    private val apiInfoP:ChangeInfoApi,
    private val apiPasswordApi: UpdatePasswordApi,
    private val apiAvatarApi: UpdateAvatarApi
):ChangeInfoPersonalRetrofitRepository{
    override suspend fun updateInfoPersonal(
        email: String,
        name: String
    ): Response<GenericResponse>? {
        var response: Response<GenericResponse>? = null
        try {
            response = apiInfoP.changeInfoPersonal(email, name)
            Log.v("buysharedLog", "response:"+response.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }

    override suspend fun updateAvatar(file: File): Response<UpdateAvatarResponse>? {
        var responseAvatar: Response<UpdateAvatarResponse>? = null
        try {
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            responseAvatar = apiAvatarApi.changeInfoPersonal(requestFile)
            Log.v("buysharedLog", "response:"+responseAvatar.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return responseAvatar
    }

    override suspend fun updatePassword(
        passwordAnt: String,
        passwordNew: String,
        passwordRepetNew: String
    ): Response<GenericResponse>? {
        var responsePassword: Response<GenericResponse>? = null
        try {
            responsePassword = apiPasswordApi.updatePassword(passwordAnt,passwordNew, passwordRepetNew)
            Log.v("buysharedLog", "response:"+responsePassword.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return responsePassword
    }
}