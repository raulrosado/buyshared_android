package com.example.buyshared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.User

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        User::class
    ]
)
abstract class db : RoomDatabase() {
    abstract val userDao :UserDao
}