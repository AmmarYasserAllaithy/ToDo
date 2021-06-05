package app.ammar.todo.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ammar.todo.R
import app.ammar.todo.databinding.ActivityWelcomeBinding


class Welcome : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    private var username: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        // Shared Preferences
        val sharedPref = getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE)
        username = sharedPref.getString(getString(R.string.pref_user_key), null)


        with(binding) {
            doneBtn.setOnClickListener {
                if (!nameEt.text.isNullOrBlank()) {
                    with(sharedPref.edit()) {
                        putString(getString(R.string.pref_user_key), nameEt.text.toString())
                        apply()
                    }
                    gotoMain()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Prevent infinite loop when coming form MainActivity
        if (callingActivity == null) username?.let { gotoMain() }
        else if (parentActivityIntent == MainActivity().intent) binding.nameEt.setText(username)
    }


    private fun gotoMain() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

}
