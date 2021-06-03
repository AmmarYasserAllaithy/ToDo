package app.ammar.todo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.ammar.todo.data.repository.TodoRepository
import app.ammar.todo.ui.main.viewmodel.MainViewModel


class MainViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
