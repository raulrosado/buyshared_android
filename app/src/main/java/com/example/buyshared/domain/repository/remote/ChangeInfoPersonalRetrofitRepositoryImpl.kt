package com.example.buyshared.domain.repository.remote

import android.Manifest
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.example.buyshared.core.Function
import com.example.buyshared.data.remote.services.ChangeInfoApi
import com.example.buyshared.data.remote.services.UpdateAvatarApi
import com.example.buyshared.data.remote.services.UpdatePasswordApi
import com.example.buyshared.data.retrofitObjet.GenericResponse
import com.example.buyshared.data.retrofitObjet.UpdateAvatarResponse
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.ByteString.Companion.encode
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


import javax.inject.Inject


class ChangeInfoPersonalRetrofitRepositoryImpl @Inject constructor(
    private val apiInfoP: ChangeInfoApi,
    private val apiPasswordApi: UpdatePasswordApi,
    private val apiAvatarApi: UpdateAvatarApi
) : ChangeInfoPersonalRetrofitRepository {
    override suspend fun updateInfoPersonal(
        email: String,
        name: String
    ): Response<GenericResponse>? {
        var response: Response<GenericResponse>? = null
        try {
            response = apiInfoP.changeInfoPersonal(email, name)
            Log.v("buysharedLog", "response:" + response.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return response
    }

    override suspend fun updateAvatar(
        uriFile: Uri,
        context: Activity
    ): Response<UpdateAvatarResponse>? {
        var responseAvatar: Response<UpdateAvatarResponse>? = null
        try {
            val function = Function()
            val file = File(function.getRealPathFromURI(context!!, uriFile!!))
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val part = MultipartBody.Part.createFormData("file",file.name,requestBody)
            responseAvatar = apiAvatarApi.changeInfoPersonal(part)

            Log.v("buysharedLog", "response:" + responseAvatar.toString())
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
            responsePassword =
                apiPasswordApi.updatePassword(passwordAnt, passwordNew, passwordRepetNew)
            Log.v("buysharedLog", "response:" + responsePassword.toString())
        } catch (e: HttpException) {
            Log.v("buysharedLog", "Error, problema en la consulta")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v("buysharedLog", "Error, problema en la consulta al servidor")
        }
        return responsePassword
    }
}