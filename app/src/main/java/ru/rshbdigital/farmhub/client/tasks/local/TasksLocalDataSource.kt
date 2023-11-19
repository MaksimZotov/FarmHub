package ru.rshbdigital.farmhub.client.tasks.local

import ru.rshbdigital.farmhub.core.database.dao.TaskDao
import ru.rshbdigital.farmhub.core.database.model.DBTask
import javax.inject.Inject

class TasksLocalDataSource @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend fun saveTasks(tasks: List<DBTask>) {
        taskDao.saveAll(tasks)
    }

    suspend fun saveTask(task: DBTask) {
        taskDao.save(task)
    }

    suspend fun getAllTasks(): List<DBTask> {
        return taskDao.getAll()
    }
}