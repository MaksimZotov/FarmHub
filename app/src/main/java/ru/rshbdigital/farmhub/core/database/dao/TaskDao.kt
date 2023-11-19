package ru.rshbdigital.farmhub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.rshbdigital.farmhub.core.database.model.DBTask

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(dbTasks: List<DBTask>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(dbTask: DBTask)

    @Transaction
    @Query("SELECT * FROM task_table")
    fun getAll(): List<DBTask>
}