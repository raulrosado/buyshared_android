package com.example.buyshared.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Task")
class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "__v") val __v: Int,
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "estado") val estado: Int,
    @ColumnInfo(name = "id_evento") val id_evento: String,
    @ColumnInfo(name = "id_lista") val id_lista: String,
    @ColumnInfo(name = "id_user") val id_user: String,
    @ColumnInfo(name = "referencia") val referencia: String,
    @ColumnInfo(name = "texto") val texto: String
):Serializable