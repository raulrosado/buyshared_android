package com.example.buyshared.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buyshared.data.retrofitObjet.Avatar
import java.io.Serializable

@Entity(tableName = "Avatars")
class AvatarsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int = 0,
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "idList") val idList: String,
    @ColumnInfo(name = "idEvent") val idEvent: String
):Serializable{
    fun toAvatar():Avatar{
        return Avatar(_id, avatar)
    }
}