package com.example.buyshared.domain.repository

import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.UserEntity

class UserReposityImpl (
    private val dao: UserDao
) : UserRepository {
    override fun insert(user: UserEntity) {
        dao.insert(user)
    }

    override fun update(user: UserEntity) {
        dao.update(user)
    }

    override fun delete(user: UserEntity) {
        dao.delete(user)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }
}