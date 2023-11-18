package ru.rshbdigital.farmhub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.rshbdigital.farmhub.core.database.model.DBRequest

@Dao
interface RequestDao {

    @Query("SELECT * FROM request_table")
    fun getAllFlow(): Flow<List<DBRequest>>

    @Transaction
    @Query("SELECT * FROM request_table")
    suspend fun getAll(): List<DBRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(dbRequest: DBRequest)

    @Update
    suspend fun update(dbRequest: DBRequest)

    @Query("DELETE FROM request_table WHERE id = :id")
    suspend fun delete(id: Long)
}