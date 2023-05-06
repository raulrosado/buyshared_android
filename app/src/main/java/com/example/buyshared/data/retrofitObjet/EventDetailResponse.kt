package com.example.buyshared.data.retrofitObjet

data class EventDetailResponse(
    val __v: Int,
    val _id: String,
    val avatar: List<Avatar>,
    val bg: String,
    val cant: Int,
    val complet: Double,
    val estado: Int,
    val id_user: String,
    val nombre: String,
    val referencia: String,
    val task: List<Task>,
    val taskReferencia: List<Any>
)