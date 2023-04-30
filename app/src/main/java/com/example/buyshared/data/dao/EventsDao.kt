package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.EventsEntity

@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: EventsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(eventos:List<EventsEntity>)

    @Update
    suspend fun update(events: EventsEntity)

    @Delete
    suspend fun delete(events: EventsEntity)

    @Query("DELETE FROM Events")
    suspend fun deleteAll()

    @Query("Select * From Events")
    suspend fun getAll() : List<EventsEntity>

}