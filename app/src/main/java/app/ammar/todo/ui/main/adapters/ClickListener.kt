package app.ammar.todo.ui.main.adapters

import app.ammar.todo.data.model.Todo


class ClickListener(
    val todoClickListener: (todo: Todo) -> Unit,
    val checkBoxClickListener: (id: Int) -> Unit
) {
    fun onClick(todo: Todo) = todoClickListener(todo)
    fun toggleDone(todo: Todo) = checkBoxClickListener(todo.ID)
}
