package com.example.androidtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    fun getTodos(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Todo>> =
        when(sortOrder) {
            SortOrder.BY_DATE -> getTodosSortedByDateCreated(query, hideCompleted)
            SortOrder.BY_NAME -> getTodosSortedByName(query, hideCompleted)
        }

    @Query("SELECT * FROM todo_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, name")
    fun getTodosSortedByName(searchQuery: String, hideCompleted: Boolean) : Flow<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, created")
    fun getTodosSortedByDateCreated(searchQuery: String, hideCompleted: Boolean) : Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : Todo)

    @Update
    suspend fun update(todo : Todo)

    @Delete
    suspend fun delete(todo : Todo)


}