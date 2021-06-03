package app.ammar.todo.ui.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import app.ammar.todo.data.model.Todo


class RecyclerAdapter(private val clickListener: ClickListener) :
    ListAdapter<Todo, ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

}
