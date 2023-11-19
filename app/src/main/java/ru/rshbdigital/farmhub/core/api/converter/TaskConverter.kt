package ru.rshbdigital.farmhub.core.api.converter

import ru.rshbdigital.farmhub.core.api.model.NWTask
import ru.rshbdigital.farmhub.core.database.model.DBTask
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.util.let
import ru.rshbdigital.farmhub.core.util.nullIfBlank

object TaskConverter {

    fun fromNetwork(src: NWTask): Task? {
        val operation = convert(src.operation, OperationConverter::fromNetwork)
        val machine = convert(src.machine, MachineConverter::fromNetwork)
        val unit = convert(src.unit, TrailingUnitConverter::fromNetwork)
        val location = convert(src.location, LocationConverter::fromNetwork)
        val executor = convert(src.executor, UserConverter::fromNetwork)

        return let(
            src.id.nullIfBlank(),
            operation,
            src.addDate,
            src.commitDate,
            machine,
            unit,
            location,
            executor
        ) { id, operation, addDate, commotDate, machine, unit, location, executor ->
            Task(
                id = id,
                taskAddedDate = convert(src.addDate, DateConverter::fromNetwork),
                commitDate = convert(src.commitDate, DateConverter::fromNetwork),
                operation = operation,
                status = convertEnum<Task.Status>(src.status) ?: Task.Status.UNKNOWN,
                payment = src.payment,
                machine = machine,
                unit = unit,
                location = location,
                executor = executor,
                comment = src.comment,
            )
        }
    }

    fun toNetwork(src: Task): NWTask = NWTask(
        id = src.id,
        addDate = convert(src.taskAddedDate, DateConverter::toNetwork),
        commitDate = convert(src.commitDate, DateConverter::toNetwork),
        operation = convert(src.operation, OperationConverter::toNetwork),
        status = src.status.name,
        payment = src.payment,
        machine = convert(src.machine, MachineConverter::toNetwork),
        unit = convert(src.unit, TrailingUnitConverter::toNetwork),
        location = convert(src.location, LocationConverter::toNetwork),
        executor = convert(src.executor, UserConverter::toNetwork),
        comment = src.comment,
    )

    fun toEntity(src: Task): DBTask = DBTask(
        id = src.id,
        taskAddedDate = src.taskAddedDate,
        commitDate = src.commitDate,
        operation = src.operation,
        status = src.status,
        payment = src.payment,
        machine = src.machine,
        unit = src.unit,
        location = src.location,
        executor = src.executor,
        comment = src.comment,
    )

    fun fromEntity(src: DBTask) = Task(
        id = src.id,
        taskAddedDate = src.taskAddedDate,
        commitDate = src.commitDate,
        operation = src.operation,
        status = src.status,
        payment = src.payment,
        machine = src.machine,
        unit = src.unit,
        location = src.location,
        executor = src.executor,
        comment = src.comment,
    )
}
