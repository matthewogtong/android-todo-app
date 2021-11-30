package com.example.androidtodoapp.util

import android.view.View
import androidx.appcompat.widget.SearchView

fun View.onQueryTextChanged(listener: (String) -> Unit) {
    (this as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}