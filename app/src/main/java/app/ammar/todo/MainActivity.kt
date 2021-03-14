package app.ammar.todo

import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.ammar.todo.databinding.ActivityMainBinding
import app.ammar.todo.fragments.DoneFragment
import app.ammar.todo.fragments.ToDoFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        // Greeting layout
//        binding.welcomeBackTV.text = getString(R.string.welcome_back, intent.getStringExtra(Welcome().NAME))
        binding.welcomeBackTV.text = getString(R.string.welcome_back, "Ammar")
        binding.weHaveTV.text = getString(R.string.we_have, 9)
        binding.hideIB.setOnClickListener { binding.greetingCLayout.visibility = GONE }

//        binding.notYetTV.setOnClickListener { binding.addFAB.performClick() }

        binding.addFAB.setOnClickListener {
            Snackbar.make(binding.root, "Hello, World! This is a SnackBar", Snackbar.LENGTH_LONG)
                .setAnchorView(binding.addFAB)
                .setAction("Close") {
                    Toast.makeText(applicationContext, "SnackBar closed", Toast.LENGTH_SHORT)
                        .show()
                }
                .show()
        }
        binding.bottomNavigationView.background = null

        setAdapters()
    }

    private fun setAdapters() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(ToDoFragment(), "ToDo")
        adapter.addFragment(DoneFragment(), "Done")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}