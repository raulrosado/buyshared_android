package com.example.buyshared.domain.repository

import com.example.buyshared.data.model.UserEntity

interface UserRepository {
    fun insert(user: UserEntity)
    fun update(user: UserEntity)
    fun delete(user: UserEntity)
    fun deleteAll()
}