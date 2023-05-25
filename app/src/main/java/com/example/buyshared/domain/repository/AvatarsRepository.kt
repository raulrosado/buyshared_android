package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.data.retrofitObjet.AvatarList

interface AvatarsRepository {
    fun insert(avatarsEntity: AvatarsEntity)

    fun update(avatarsEntity: AvatarsEntity)

    fun delete(avatarsEntity: AvatarsEntity)
    fun getAvatarsByIdList(id:String):List<Avatar>

    suspend fun getAll():List<AvatarsEntity>
    fun deleteAll()

    fun deleteById(id:String)
}