package com.example.to_do_list.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TodoDao {

    //get all
    @Query("SELECT * FROM TodoEntity")
    fun getAllTodo() : List<TodoEntity>

    //insert todo
    fun insertTodo(todo : TodoEntity)

    //delete todo
    fun deleteTodo(todo : TodoEntity)
}