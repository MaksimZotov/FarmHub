package ru.rshbdigital.farmhub.core.database.converter

import ru.rshbdigital.farmhub.core.database.converter.base.BaseConverter
import ru.rshbdigital.farmhub.core.model.Location
import ru.rshbdigital.farmhub.core.model.Machine
import ru.rshbdigital.farmhub.core.model.Operation
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.model.TrailingUnit
import ru.rshbdigital.farmhub.core.model.User
import java.util.Date

class DateConverter : BaseConverter<Date>()
class OperationConverter : BaseConverter<Operation>()
class TaskStatusConverter : BaseConverter<Task.Status>()
class MachineConverter : BaseConverter<Machine>()
class LocationConverter : BaseConverter<Location>()
class UserConverter : BaseConverter<User>()
class TrailingUnitConverter : BaseConverter<TrailingUnit>()