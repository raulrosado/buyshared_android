package com.example.buyshared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buyshared.data.dao.AvatarsDao
import com.example.buyshared.data.dao.EventsDao
import com.example.buyshared.data.dao.ListasDao
import com.example.buyshared.data.dao.TaskDao
import com.example.buyshared.data.dao.UserDao
import com.example.buyshared.data.model.AvatarsEntity
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.data.model.UserEntity

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        UserEntity::class,
        EventsEntity::class,
        ListsEntity::class,
        TaskEntity::class,
        AvatarsEntity::class
    ]
)
abstract class db : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getEventsDao(): EventsDao
    abstract fun getListsDao(): ListasDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getAvatarsDao(): AvatarsDao

}