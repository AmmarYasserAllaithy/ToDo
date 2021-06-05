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
import app.ammar.todo.databinding.DialogConfirmBinding
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
            val dialogBinding = DialogConfirmBinding.inflate(layoutInflater)

            val dialog = AlertDialog.Builder(this, R.style.ConfirmDialog)
                .setView(dialogBinding.root)
                .show()

            dialogBinding.apply {
                headerTv.text = getString(R.string.delete)
                bodyTv.text = """Do you really want to delete this ToDo?
                    |This action is unrecoverable!""".trimMargin()

                noBtn.apply {
                    text = getString(R.string.keep)

                    setOnClickListener { dialog.dismiss() }
                }

                yesBtn.apply {
                    text = getString(R.string.delete)

                    setOnClickListener {
                        lifecycleScope.launch { repository.delete(binding.todo!!) }
                        finish()
                    }
                }
            }

            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun setTodo() = lifecycleScope.launch {
        binding.todo = repository.get((intent.getSerializableExtra(EXTRA.TODO) as Todo).ID)
    }

}
