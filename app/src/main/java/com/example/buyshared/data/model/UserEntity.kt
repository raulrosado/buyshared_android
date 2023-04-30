package com.example.buyshared.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User")
class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "__v") val __v: Int,
    @ColumnInfo(name = "apellidos") val apellidos: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "estado") val estado: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "token") val token: String
):Serializable