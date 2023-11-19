package ru.rshbdigital.farmhub.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rshbdigital.farmhub.core.database.dao.RequestDao
import ru.rshbdigital.farmhub.core.database.dao.TaskDao
import ru.rshbdigital.farmhub.core.database.model.DBRequest
import ru.rshbdigital.farmhub.core.database.model.DBTask

@Database(
    entities = [
        DBRequest::class,
        DBTask::class
    ],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "FARM_HUB_DB"
    }

    abstract fun requestDao(): RequestDao
    abstract fun taskDao(): TaskDao
}