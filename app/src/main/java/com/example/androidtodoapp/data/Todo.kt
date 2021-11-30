package com.example.androidtodoapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "todo_table")
@Parcelize
data class Todo(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis()

) : Parcelable {

    val createdDataFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)

}