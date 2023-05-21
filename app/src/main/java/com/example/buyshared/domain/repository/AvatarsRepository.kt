package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.retrofitObjet.AvatarX

interface AvatarsRepository {
    fun insert(avatarsEntity: AvatarsEntity)

    fun update(avatarsEntity: AvatarsEntity)

    fun delete(avatarsEntity: AvatarsEntity)
    fun getAvatarsByIdList(id:String):List<AvatarsEntity>

    suspend fun getAll():List<AvatarsEntity>
    fun deleteAll()

    fun deleteById(id:String)
}