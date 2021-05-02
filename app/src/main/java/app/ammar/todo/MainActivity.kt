package app.ammar.todo

import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import app.ammar.todo.adapters.ViewPagerAdapter
import app.ammar.todo.databinding.ActivityMainBinding
import app.ammar.todo.fragments.AddBSDialogFragment
import app.ammar.todo.fragments.DoneFragment
import app.ammar.todo.fragments.ToDoFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        setupViewPager()

        // Greeting layout
        binding.welcomeBackTV.text =
            getString(R.string.welcome_back, intent.getStringExtra(Welcome().NAME))
        binding.weHaveTV.text = getString(R.string.we_have, 9)
        binding.hideIB.setOnClickListener { binding.greetingCLayout.visibility = GONE }

        binding.addFAB.setOnClickListener {
            AddBSDialogFragment().show(supportFragmentManager, AddBSDialogFragment.TAG)
        }

    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(ToDoFragment(), "ToDo")
        adapter.addFragment(DoneFragment(), "Done")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}