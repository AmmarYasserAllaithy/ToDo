package app.ammar.todo.ui.main.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.ammar.todo.R
import app.ammar.todo.data.model.Todo
import app.ammar.todo.data.model.TodoDatabase
import app.ammar.todo.data.repository.TodoRepository
import app.ammar.todo.databinding.ActivityDetailsBinding
import app.ammar.todo.ui.main.view.fragments.AddBSDFragment
import app.ammar.todo.utils.EXTRA
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var repository: TodoRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        repository = TodoRepository(TodoDatabase.getDatabase(this).dao)
    }

    override fun onStart() {
        super.onStart()
        setTodo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.edit -> {
            AddBSDFragment { setTodo() }
                .show(supportFragmentManager, "${javaClass.canonicalName}TAG")
            true
        }
        R.id.delete -> {
            AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_cancel_24)
                .setTitle("Delete")
                .setMessage("Do you want to delete this ToDo?")
                .setCancelable(false)
                .setPositiveButton("Keep") { _, _ -> }
                .setNegativeButton("Delete") { _, _ ->
                    lifecycleScope.launch { repository.delete(binding.todo!!) }
                    finish()
                }
                .show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun setTodo() = lifecycleScope.launch {
        binding.todo = repository.get((intent.getSerializableExtra(EXTRA.TODO) as Todo).ID)
    }

}
