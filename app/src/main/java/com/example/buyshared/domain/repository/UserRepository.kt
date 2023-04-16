package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.User

interface UserRepository {
    fun insert(user: User)

    fun update(user: User)

    fun delete(user: User)

    fun deleteAll()
}