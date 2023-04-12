package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(usuario: User)

    @Update
    fun update(usuarios: User)

    @Delete
    fun delete(usuarios: User)

    @Query("DELETE FROM User")
    fun deleteAll()
}