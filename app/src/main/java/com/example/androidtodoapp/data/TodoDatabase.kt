package com.example.androidtodoapp.data

import androidx.room.Database


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase {

    abstract fun todoDao() : TodoDao

}