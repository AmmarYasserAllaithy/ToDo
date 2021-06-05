package app.ammar.todo.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ammar.todo.R
import java.util.*
import kotlin.concurrent.schedule


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer("Splash").schedule(3000) {
            startActivity(Intent(applicationContext, Welcome::class.java))
            finish()
        }
    }

}
