package com.example.androidtodoapp.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidtodoapp.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoDao : TodoDao
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val todos = todoDao.getTodos("bla").asLiveData()

}