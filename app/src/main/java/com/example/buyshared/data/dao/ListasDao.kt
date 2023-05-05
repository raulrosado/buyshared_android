package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity

@Dao
interface ListasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lists: ListsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lists:List<ListsEntity>)

    @Update
    suspend fun update(lists: ListsEntity)

    @Delete
    suspend fun delete(lists: ListsEntity)

    @Query("DELETE FROM Lists")
    suspend fun deleteAll()

    @Query("Select * From Lists")
    suspend fun getAll() : List<ListsEntity>

    @Query("Select * from Lists where _id=:id")
    fun getById(id:String): ListsEntity
}