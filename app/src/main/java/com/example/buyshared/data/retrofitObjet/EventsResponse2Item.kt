package com.example.buyshared.data.retrofitObjet

data class EventsResponse2Item(
    val __v: Int,
    val _id: String,
    val avatars: List<AvatarEvent>,
    val bg: String,
    val cant: Int,
    val complet: Int,
    val estado: Int,
    val id_user: String,
    val nombre: String,
    val referencia: String,
    val taskcomplet: Int
)