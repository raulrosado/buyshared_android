package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(usuario: UserEntity)

    @Update
    fun update(usuarios: UserEntity)

    @Delete
    fun delete(usuarios: UserEntity)

    @Query("DELETE FROM User")
    fun deleteAll()
}