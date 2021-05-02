package app.ammar.todo.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.ammar.todo.database.ToDo
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("title")
fun TextView.setTitle(todo: ToDo?) = todo?.let { text = todo.title }

@BindingAdapter("desc")
fun TextView.setDesc(todo: ToDo?) = todo?.let { text = todo.desc }

@BindingAdapter("date")
fun TextView.setDate(todo: ToDo?) = todo?.let {
    text = SimpleDateFormat("E, MMM dd y, HH:mm", Locale.getDefault()).format(Date(todo.deadline))
}
