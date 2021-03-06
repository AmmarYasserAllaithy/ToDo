package app.ammar.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Welcome : AppCompatActivity() {

    val NAME: String = "USER_NAME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val nameET: EditText = findViewById(R.id.name_et)
        val doneBTN: Button = findViewById(R.id.done_btn)

        doneBTN.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NAME, nameET.text.trim().toString())
            startActivity(intent)
            finish()
        }
    }
}