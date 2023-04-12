package com.example.buyshared.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class User (
    @PrimaryKey
    val _id: String,
    val __v: Int,
    val apellidos: String,
    val avatar: String,
    val email: String,
    val estado: Int,
    val name: String,
    val password: String,
    val role: String,
    val token: String
)