package com.example.buyshared.data.remote.services

import com.example.buyshared.data.retrofitObjet.LoginResponse
import com.example.buyshared.data.retrofitObjet.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApiClient {
    @FormUrlEncoded
    @POST("add_user")
    suspend fun postRegisterRequest(
        @Field("firstName") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>
}