package com.example.buyshared.data.dao

import androidx.room.*
import com.example.buyshared.data.model.TaskEntity

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

    @Query("Select * from Task where id_evento =:id")
    fun getTasksByIdEvent(id:String):List<TaskEntity>

    @Query("Delete from Task where id_lista =:id")
    fun delTasksByIdList(id:String)

    @Query("Delete from Task where id_evento =:id")
    fun delTasksByIdEvent(id:String)

    @Query("Select * from Task")
    fun getAllTasks():List<TaskEntity>

    @Query("Update Task Set estado = :estado where _id =:id")
    fun completTask(id:String, estado: Int)


}