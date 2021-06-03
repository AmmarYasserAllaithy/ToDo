package app.ammar.todo.data.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodoDatabaseDao {

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)


    @Query("SELECT * FROM todo_table WHERE ID = :id")
    suspend fun get(id: Int): Todo?

    @Query("SELECT * FROM todo_table WHERE done = 0 ORDER BY deadline")
    fun getAllTodo(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE done = 1 ORDER BY deadline DESC")
    fun getAllDone(): LiveData<List<Todo>>


    @Query("SELECT count(ID) FROM todo_table WHERE done = 0 AND deadline <= :deadline")
    suspend fun countTodoOverdueBy(deadline: Long): Int

    @Query("UPDATE todo_table SET done = :done WHERE ID = :id")
    suspend fun done(id: Int, done: Boolean = true): Int

}
