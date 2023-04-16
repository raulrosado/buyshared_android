package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiClient {
    @FormUrlEncoded
    @POST("login")
    suspend fun postLoginRequest(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}