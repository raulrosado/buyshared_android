package com.example.buyshared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buyshared.data.dao.EventsDao
import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.UserEntity

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        UserEntity::class,
        EventsEntity::class
    ]
)
abstract class db : RoomDatabase() {
    abstract fun getUserDao() :UserDao
    abstract fun getEventsDao() : EventsDao
}