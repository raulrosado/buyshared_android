package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.GenericResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UpdatePasswordApi {
    @FormUrlEncoded
    @POST("user/postChangePassword")
    suspend fun updatePassword(
        @Field("passwordAnt") passwordAnt: String,
        @Field("passwordNew") passwordNew: String,
        @Field("passwordRepetNew") passwordRepetNew: String,
    ):Response<GenericResponse>
}