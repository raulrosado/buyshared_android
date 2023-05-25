package com.example.buyshared.domain.repository

import com.example.buyshared.data.dao.AvatarsDao
import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.retrofitObjet.Avatar
import javax.inject.Inject

class AvatarsRepositoryImpl @Inject constructor(
    private val dao:AvatarsDao
) :AvatarsRepository{
    override fun insert(avatarsEntity: AvatarsEntity) {
        dao.insert(avatarsEntity)
    }

    override fun update(avatarsEntity: AvatarsEntity) {
        dao.update(avatarsEntity)
    }

    override fun delete(avatarsEntity: AvatarsEntity) {
        dao.delete(avatarsEntity)
    }

    override fun getAvatarsByIdList(id: String): List<Avatar> {
        return dao.selectByIdList(id)
    }

    override suspend fun getAll(): List<AvatarsEntity> {
        return dao.getAllAvatars()
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun deleteById(id: String) {
        dao.deleteById(id)
    }


}