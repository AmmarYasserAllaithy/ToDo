package app.ammar.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.ammar.todo.database.ToDo
import app.ammar.todo.databinding.ItemBinding


class RecyclerAdapter(private val clickListener: ClickListener) :
    ListAdapter<ToDo, RecyclerAdapter.ViewHolder>(DiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)


    class ViewHolder private constructor(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ToDo, clickListener: ClickListener) = with(binding) {
            todo = item
            this.clickListener = clickListener
            executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

}


class DiffCallback : DiffUtil.ItemCallback<ToDo>() {

    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem.ID == newItem.ID

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem

}


class ClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(todo: ToDo) = clickListener(todo.ID)
}
