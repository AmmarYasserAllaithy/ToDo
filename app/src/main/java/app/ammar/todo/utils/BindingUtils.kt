package app.ammar.todo.utils

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.ammar.todo.data.model.Todo


@BindingAdapter("title")
fun TextView.setTitle(todo: Todo?) = todo?.let { text = it.title }

@BindingAdapter("desc")
fun TextView.setDesc(todo: Todo?) = todo?.let { text = it.desc }

@BindingAdapter("brief_desc")
fun TextView.setBriefDesc(todo: Todo?) = todo?.let { text = it.desc.replace("\n", " ") }

@BindingAdapter("date")
fun TextView.setDate(todo: Todo?) = todo?.let { text = it.deadline.fullDate() }

@BindingAdapter("date_only")
fun TextView.setDateOnly(todo: Todo?) = todo?.let { text = it.deadline.date() }

@BindingAdapter("time_only")
fun TextView.setTimeOnly(todo: Todo?) = todo?.let { text = it.deadline.time() }

@BindingAdapter("done")
fun CheckBox.isDone(todo: Todo?) = todo?.let { isChecked = it.done }
