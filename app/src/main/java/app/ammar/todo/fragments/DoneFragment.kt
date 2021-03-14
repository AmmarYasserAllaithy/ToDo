package app.ammar.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.ammar.todo.R
import app.ammar.todo.databinding.FragmentBinding

class DoneFragment : Fragment() {

    lateinit var binding: FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinding.inflate(inflater)

        binding.notYetTV.text = getString(R.string.not_yet, "Done")

        return binding.root
    }
}
