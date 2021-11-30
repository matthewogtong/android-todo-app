package com.example.androidtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    class Callback @Inject constructor(
        private val database : Provider<TodoDatabase>,
        private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().todoDao()

            applicationScope.launch {

                dao.insert(Todo(name = "Fold laundry"))
                dao.insert(Todo(name = "Wash dishes"))
                dao.insert(Todo(name = "Practice Android Development", important = true))
                dao.insert(Todo(name = "Purchase groceries"))
                dao.insert(Todo(name = "Exercise", completed = true))

            }
        }

    }

}