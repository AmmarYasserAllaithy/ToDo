package app.ammar.todo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ammar.todo.data.repository.TodoRepository
import kotlinx.coroutines.launch


class DoneViewModel(private val repository: TodoRepository) : ViewModel() {

    val list = repository.getAllDone()

    private val _makeToDo = MutableLiveData<Int?>()
    val makeToDo: LiveData<Int?> get() = _makeToDo


    fun onCheckBoxClicked(id: Int) = _makeToDo.postValue(id)

    fun makeToDo(id: Int) = viewModelScope.launch {
        if (repository.makeToDo(id) > 0) _makeToDo.postValue(null)
    }

}
