package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.data.model.UserEntity

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:TaskEntity)

    @Update
    fun update(task:TaskEntity)

    @Delete
    fun delete(task:TaskEntity)

    @Query("DELETE FROM Task")
    fun deleteAll()

    @Query("Select * from Task where id_lista =:id")
    fun getTasksByIdList(id:String):List<TaskEntity>

    @Query("Delete from Task where id_lista =:id")
    fun delTasksByIdList(id:String)

    @Query("Select * from Task")
    fun getAllTasks():List<TaskEntity>
}