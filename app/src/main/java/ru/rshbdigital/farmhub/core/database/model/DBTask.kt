package ru.rshbdigital.farmhub.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.rshbdigital.farmhub.core.database.converter.DateConverter
import ru.rshbdigital.farmhub.core.database.converter.LocationConverter
import ru.rshbdigital.farmhub.core.database.converter.MachineConverter
import ru.rshbdigital.farmhub.core.database.converter.OperationConverter
import ru.rshbdigital.farmhub.core.database.converter.TaskStatusConverter
import ru.rshbdigital.farmhub.core.database.converter.TrailingUnitConverter
import ru.rshbdigital.farmhub.core.database.converter.UserConverter
import ru.rshbdigital.farmhub.core.model.Location
import ru.rshbdigital.farmhub.core.model.Machine
import ru.rshbdigital.farmhub.core.model.Operation
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.model.TrailingUnit
import ru.rshbdigital.farmhub.core.model.User
import java.util.Date

@Entity(tableName = "task_table")
@TypeConverters(
    DateConverter::class,
    OperationConverter::class,
    TaskStatusConverter::class,
    MachineConverter::class,
    LocationConverter::class,
    UserConverter::class,
    TrailingUnitConverter::class
)
data class DBTask(
    @PrimaryKey
    val id: String,
    val taskAddedDate: Date?,
    val commitDate: Date?,
    val operation: Operation,
    val status: Task.Status,
    val payment: Float?,
    val machine: Machine,
    val unit: TrailingUnit,
    val location: Location,
    val executor: User,
    val comment: String?,
    val author: User
)