package com.example.buyshared.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Lists")
class ListsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "_id") val _id:String,
    @ColumnInfo(name = "__v") val __v:Int,
    @ColumnInfo(name = "cant") val cant:Int,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "referencia") val referencia:String,
    @ColumnInfo(name = "estado") val estado:Int,
    @ColumnInfo(name = "id_user") val id_user:String,
):Serializable