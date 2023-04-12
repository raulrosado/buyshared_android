package com.example.buyshared.data.retrofitObjet

data class LoginResponse(
    val status: Boolean,
    val token: String,
    val user: User
)