package com.example.buyshared.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.buyshared.data.model.AvatarsEntity

@Dao
interface AvatarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(avatarsEntity: AvatarsEntity)

    @Update
    fun update(avatarsEntity: AvatarsEntity)

    @Delete
    fun delete(avatarsEntity: AvatarsEntity)

    @Query("DELETE FROM Avatars")
    fun deleteAll()

    @Query("DELETE FROM Avatars where idList =:id")
    fun deleteById(id:String)

    @Query("Select * from Avatars")
    suspend fun getAllAvatars():List<AvatarsEntity>

    @Query("Select * from Avatars where idList = :id")
    fun selectByIdList(id:String):List<AvatarsEntity>
}