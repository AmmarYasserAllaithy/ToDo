package app.ammar.todo.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.ammar.todo.R
import app.ammar.todo.data.model.TodoDatabase
import app.ammar.todo.data.repository.TodoRepository
import app.ammar.todo.databinding.ActivityMainBinding
import app.ammar.todo.ui.base.MainViewModelFactory
import app.ammar.todo.ui.main.adapters.ViewPagerAdapter
import app.ammar.todo.ui.main.view.fragments.AddBSDFragment
import app.ammar.todo.ui.main.view.fragments.DoneFragment
import app.ammar.todo.ui.main.view.fragments.ToDoFragment
import app.ammar.todo.ui.main.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setupViewPager()
        setupViewModel()
        setupForTodayObserver()

        // Greeting layout
        val username = getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE)
            .getString(getString(R.string.pref_user_key), null)


        with(binding) {
            welcomeBackTV.text = getString(R.string.welcome_back, username)

            hideIB.setOnClickListener { greetingCLayout.visibility = GONE }

            addFAB.setOnClickListener {
                AddBSDFragment { updateForToday() }
                    .show(supportFragmentManager, "${javaClass.canonicalName}TAG")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateForToday()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_edit_user -> {
            gotoWelcome()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.add(ToDoFragment(), "ToDo")
        adapter.add(DoneFragment(), "Done")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun setupViewModel() {
        val repository = TodoRepository(TodoDatabase.getDatabase(application).dao)
        val factory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setupForTodayObserver() = viewModel.forToday.observe(this, {
        it?.run { binding.welcomeNumTV.text = getString(R.string.welcome_num, it) }
    })

    private fun updateForToday() = viewModel.forToday()

    private fun gotoWelcome() {
        startActivityForResult(Intent(this, Welcome::class.java), 1)
        finish()
    }

}
