package com.example.androidtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun getTodos() : Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : Todo)

    @Update
    suspend fun update(todo : Todo)

    @Delete
    suspend fun delete(todo : Todo)


}