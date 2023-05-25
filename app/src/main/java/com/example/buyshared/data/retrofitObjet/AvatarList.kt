package com.example.buyshared.data.retrofitObjet

import com.example.buyshared.data.model.AvatarsEntity

data class AvatarList(
    val _id: String,
    val avatar: String,
    val idList: String
){
    fun toAvatarEntity(): AvatarsEntity {
        return AvatarsEntity(
            0,
            _id,
            avatar,
            idList,
            ""
        )
    }
}