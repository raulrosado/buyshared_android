package com.example.buyshared.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Events")
class EventsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "_id") val _id:String,
    @ColumnInfo(name = "id_user") val id_user:String,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "bg") val bg:String,
    @ColumnInfo(name = "estado") val estado:Int,
    @ColumnInfo(name = "referencia") val referencia:String,
    @ColumnInfo(name = "__v") val __v:Int,
    @ColumnInfo(name = "cant") val cant:Int,
    @ColumnInfo(name = "complet") val complet:Int
):Serializable