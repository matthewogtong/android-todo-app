package com.example.androidtodoapp.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidtodoapp.data.PreferencesManager
import com.example.androidtodoapp.data.SortOrder
import com.example.androidtodoapp.data.Todo
import com.example.androidtodoapp.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoDao : TodoDao,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val todosEventChannel = Channel<TodosEvent>()

    val todosEvent = todosEventChannel.receiveAsFlow()

    private val todosFlow = combine(
        searchQuery,
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        todoDao.getTodos(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val todos = todosFlow.asLiveData()

    fun onSortOrderSelected(sortOrder : SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompleted(hideCompleted : Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTodoSelected(todo: Todo) {

    }

    fun onTodoCheckedChanged(todo: Todo, isChecked: Boolean) = viewModelScope.launch {
        todoDao.update(todo.copy(completed = isChecked))
    }

    fun onTodoSwiped(todo: Todo) = viewModelScope.launch {
        todoDao.delete(todo)
        todosEventChannel.send(TodosEvent.ShowUndoDeleteTodoMessage(todo))

    }

    fun onUndoDeleteClick(todo: Todo) = viewModelScope.launch {
        todoDao.insert(todo)
    }

    // sealed class is like an enum except values can hold data because they hold instances of actual classes
    sealed class TodosEvent {
        data class ShowUndoDeleteTodoMessage(val todo: Todo) : TodosEvent()
    }

}