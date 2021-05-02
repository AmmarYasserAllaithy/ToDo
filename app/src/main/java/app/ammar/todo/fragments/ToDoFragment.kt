package app.ammar.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.ammar.todo.R
import app.ammar.todo.adapters.ClickListener
import app.ammar.todo.adapters.RecyclerAdapter
import app.ammar.todo.database.DataProvider
import app.ammar.todo.databinding.FragmentBinding

class ToDoFragment : Fragment() {

    lateinit var binding: FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinding.inflate(inflater)


        val adapter = RecyclerAdapter(ClickListener {
            Toast.makeText(inflater.context, "ID = $it", Toast.LENGTH_SHORT).show()
        })
        adapter.submitList(DataProvider.toDoList)


        with(binding) {
            recyclerView.adapter = adapter

            notYetTV.text = getString(R.string.not_yet, "ToDo")
            notYetTV.visibility = if (adapter.currentList.isEmpty()) View.VISIBLE else View.GONE

            return root
        }
    }
}
