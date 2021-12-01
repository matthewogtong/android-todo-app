package com.example.androidtodoapp.ui.todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.androidtodoapp.data.Todo
import com.example.androidtodoapp.databinding.ItemTodoBinding


class TodosAdapter(private val listener: OnItemClickListener) : ListAdapter<Todo, TodosAdapter.TodoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TodoViewHolder(private val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val todo = getItem(position)
                        listener.onItemClick(todo)
                    }
                }
                checkBoxCompleted.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val todo = getItem(position)
                        listener.onCheckBoxClick(todo, checkBoxCompleted.isChecked)
                    }
                }
            }
        }

        fun bind(todo : Todo) {
            binding.apply {
                checkBoxCompleted.isChecked = todo.completed
                textViewName.text = todo.name
                textViewName.paint.isStrikeThruText = todo.completed
                labelPriority.isVisible = todo.important
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(todo: Todo)
        fun onCheckBoxClick(todo: Todo, isChecked : Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem == newItem
    }

}