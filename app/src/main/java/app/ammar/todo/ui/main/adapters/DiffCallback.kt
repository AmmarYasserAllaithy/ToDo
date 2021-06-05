package app.ammar.todo.ui.main.adapters

import androidx.recyclerview.widget.DiffUtil
import app.ammar.todo.data.model.Todo


class DiffCallback : DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem.ID == newItem.ID

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem

}
