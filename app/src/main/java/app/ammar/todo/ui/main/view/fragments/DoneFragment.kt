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
import app.ammar.todo.ui.base.DoneViewModelFactory
import app.ammar.todo.ui.main.adapters.ClickListener
import app.ammar.todo.ui.main.adapters.RecyclerAdapter
import app.ammar.todo.ui.main.view.DetailsActivity
import app.ammar.todo.ui.main.viewmodel.DoneViewModel
import app.ammar.todo.utils.EXTRA

class DoneFragment : Fragment() {

    private lateinit var binding: FragmentBinding
    private lateinit var viewModel: DoneViewModel
    private lateinit var adapter: RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Binding
        binding = FragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this


        with(binding.notYetTV) {
            text = getString(R.string.not_yet, getString(R.string.done))

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
        val factory = DoneViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(DoneViewModel::class.java)

        binding.doneViewModel = viewModel
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

        makeToDo.observe(viewLifecycleOwner, { it?.let { makeToDo(it) } })

        list.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.notYetTV.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }

}
