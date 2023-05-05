package com.example.buyshared.data.retrofitObjet

data class LoadListDetailResponse(
    val __v: Int,
    val _id: String,
    val avatarList: List<Avatar>,
    val cant: Int,
    val estado: Int,
    val id_user: String,
    val nombre: String,
    val referencia: String,
    val tasks: List<Task>
)