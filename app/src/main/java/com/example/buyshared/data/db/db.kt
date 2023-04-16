package com.example.buyshared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.User

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        User::class
    ]
)
abstract class db : RoomDatabase() {
    abstract val userDao :UserDao
}