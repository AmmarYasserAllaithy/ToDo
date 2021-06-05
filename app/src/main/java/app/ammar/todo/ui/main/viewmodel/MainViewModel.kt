package app.ammar.todo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ammar.todo.data.repository.TodoRepository
import kotlinx.coroutines.launch


class MainViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _forToday = MutableLiveData<Int?>()
    val forToday: LiveData<Int?> get() = _forToday


    fun forToday() = with(_forToday) {
        postValue(null)

        viewModelScope.launch { postValue(repository.countTodoForToday()) }
    }

}
