package app.ammar.todo.ui.main.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.ammar.todo.R
import app.ammar.todo.data.model.TodoDatabase
import app.ammar.todo.data.repository.TodoRepository
import app.ammar.todo.databinding.FragmentBinding
import app.ammar.todo.ui.base.ToDoViewModelFactory
import app.ammar.todo.ui.main.adapters.ClickListener
import app.ammar.todo.ui.main.adapters.RecyclerAdapter
import app.ammar.todo.ui.main.view.DetailsActivity
import app.ammar.todo.ui.main.viewmodel.ToDoViewModel
import app.ammar.todo.utils.EXTRA


class ToDoFragment : Fragment() {

    private lateinit var binding: FragmentBinding
    private lateinit var viewModel: ToDoViewModel
    private lateinit var adapter: RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Binding
        binding = FragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this


        with(binding.notYetTV) {
            text = getString(R.string.not_yet, getString(R.string.todo))

            setOnClickListener {
                AddBSDFragment { }
                    .show(requireActivity().supportFragmentManager, "${javaClass.canonicalName}TAG")
            }
        }

        setupViewModel()
        setupAdapter()
        setupObservers()

        return binding.root
    }


    private fun setupViewModel() {
        val application = requireNotNull(activity).application
        val repository = TodoRepository(TodoDatabase.getDatabase(application).dao)
        val factory = ToDoViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(ToDoViewModel::class.java)

        binding.todoViewModel = viewModel
    }

    private fun setupAdapter() {
        adapter = RecyclerAdapter(ClickListener(
            {
                startActivity(
                    Intent(context, DetailsActivity::class.java).putExtra(EXTRA.TODO, it)
                )
            },
            { viewModel.onCheckBoxClicked(it) }
        ))

        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() = with(viewModel) {

        makeDone.observe(viewLifecycleOwner, { it?.let { makeDone(it) } })

        list.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.notYetTV.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }

}
