package app.ammar.todo.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.ammar.todo.data.model.Todo
import app.ammar.todo.databinding.ItemBinding


class ViewHolder private constructor(private val binding: ItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup) = ViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun bind(item: Todo, clickListener: ClickListener) = with(binding) {
        todo = item
        this.clickListener = clickListener
        executePendingBindings()
    }

}
