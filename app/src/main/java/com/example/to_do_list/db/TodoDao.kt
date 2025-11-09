package com.example.to_do_list.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    //get all
        @Query("SELECT * FROM TodoEntity ORDER BY importance")
    fun getAllTodo() : List<TodoEntity>

    @Insert
    fun insertTodo(todo : TodoEntity)

    @Delete
    fun deleteTodo(todo : TodoEntity)
}