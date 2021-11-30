package com.example.androidtodoapp.ui.todos

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidtodoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {


    private val viewModel: TodoViewModel by viewModels()
}