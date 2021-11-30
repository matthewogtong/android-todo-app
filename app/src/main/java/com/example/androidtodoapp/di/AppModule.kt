package com.example.androidtodoapp.di

import android.app.Application
import androidx.room.Room
import com.example.androidtodoapp.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        app: Application
    ) = Room.databaseBuilder(app, TodoDatabase::class.java, "todo_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTodoDao(db : TodoDatabase) = db.todoDao()

}