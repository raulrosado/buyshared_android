package com.example.buyshared.domain.repository

import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.User

class UserReposityImpl (
    private val dao: UserDao
) : UserRepository {
    override fun insert(user: User) {
        dao.insert(user)
    }

    override fun update(user: User) {
        dao.update(user)
    }

    override fun delete(user: User) {
        dao.delete(user)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }
}