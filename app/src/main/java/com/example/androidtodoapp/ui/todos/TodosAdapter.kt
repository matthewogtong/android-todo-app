package com.example.androidtodoapp.ui.todos

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtodoapp.data.Todo
import com.example.androidtodoapp.databinding.ItemTodoBinding


class TodosAdapter : ListAdapter<Todo, TodosAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class TodoViewHolder(private val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo : Todo) {
            binding.apply {
                checkBoxCompleted.isChecked = todo.completed
                textViewName.text = todo.name
                textViewName.paint.isStrikeThruText = todo.completed
                labelPriority.isVisible = todo.important
            }
        }

    }

}