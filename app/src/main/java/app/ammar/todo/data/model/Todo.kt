package app.ammar.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "todo_table")
data class Todo(

    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,

    var title: String,
    var desc: String,
    var deadline: Long,

    @ColumnInfo(defaultValue = "${false}")
    val done: Boolean = false,

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val created: Long = System.currentTimeMillis()

) : Serializable
