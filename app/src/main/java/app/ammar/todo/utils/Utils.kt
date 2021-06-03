package app.ammar.todo.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


object EXTRA {
    const val TODO = "EXTRA_TODO"
}


fun snackbar(v: View, txt: String) = Snackbar.make(v, txt, Snackbar.LENGTH_SHORT).show()

fun toast(c: Context, txt: String) = Toast.makeText(c, txt, Toast.LENGTH_SHORT).show()


fun tomorrowMillis(millis: Long = System.currentTimeMillis()) = millis + 24 * 3_600_000

fun Date.tomorrow() = also { time = tomorrowMillis(time) }

fun Calendar.tomorrow() = also { timeInMillis = tomorrowMillis(timeInMillis) }


fun Long.format(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun Long.time(): String = format("HH : mm")

fun Long.date(): String = format("MMM dd, y")

fun Long.fullDate(): String = format("E, MMM dd y, HH:mm")
