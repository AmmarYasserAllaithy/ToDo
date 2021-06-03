package app.ammar.todo.data.repository

import app.ammar.todo.data.model.Todo
import app.ammar.todo.data.model.TodoDatabaseDao
import app.ammar.todo.utils.tomorrowMillis


class TodoRepository(private val dao: TodoDatabaseDao) {

    suspend fun insert(todo: Todo) = dao.insert(todo)

    suspend fun update(todo: Todo) = dao.update(todo)

    suspend fun delete(todo: Todo) = dao.delete(todo)


    suspend fun get(id: Int) = dao.get(id)

    fun getAllTodo() = dao.getAllTodo()

    fun getAllDone() = dao.getAllDone()


    suspend fun countTodoOverdueBy(deadline: Long) = dao.countTodoOverdueBy(deadline)

    suspend fun countTodoForToday() = countTodoOverdueBy(tomorrowMillis())


    suspend fun makeDone(id: Int) = dao.done(id)

    suspend fun makeToDo(id: Int) = dao.done(id, false)

}
