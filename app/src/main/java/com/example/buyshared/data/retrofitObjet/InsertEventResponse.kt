package com.example.buyshared.data.retrofitObjet

data class InsertEventResponse(
    val __v: Int,
    val _id: String,
    val avatar: List<Avatar>,
    val bg: String,
    val cant: Int,
    val complet: Any,
    val estado: Int,
    val id_user: String,
    val nombre: String,
    val referencia: String,
    val task: List<Any>,
    val taskReferencia: List<Any>
)