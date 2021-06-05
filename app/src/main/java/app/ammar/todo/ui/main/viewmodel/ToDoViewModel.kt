package app.ammar.todo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ammar.todo.data.repository.TodoRepository
import kotlinx.coroutines.launch


class ToDoViewModel(private val repository: TodoRepository) : ViewModel() {

    val list = repository.getAllTodo()

    private val _makeDone = MutableLiveData<Int?>()
    val makeDone: LiveData<Int?> get() = _makeDone


    fun onCheckBoxClicked(id: Int) = _makeDone.postValue(id)

    fun makeDone(id: Int) = viewModelScope.launch {
        if (repository.makeDone(id) > 0) _makeDone.postValue(null)
    }

}
