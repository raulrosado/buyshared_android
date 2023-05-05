package com.example.buyshared.data.retrofitObjet

import com.example.buyshared.data.model.TaskEntity

data class Task(
    val __v: Int,
    val _id: String,
    val estado: Int,
    val id_evento: String,
    val id_lista: String,
    val id_user: String,
    val referencia: String,
    val texto: String
){
    fun toTaskEntity():TaskEntity{
        return TaskEntity(
            0,
            __v,
            _id,
            estado,
            id_evento,
            id_lista,
            id_user,
            referencia,
            texto
        )
    }
}