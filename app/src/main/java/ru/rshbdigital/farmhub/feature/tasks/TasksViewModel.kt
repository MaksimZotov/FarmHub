package ru.rshbdigital.farmhub.feature.tasks

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import ru.rshbdigital.farmhub.R.string
import ru.rshbdigital.farmhub.client.tasks.TasksRepository
import ru.rshbdigital.farmhub.core.api.converter.OperationConverter
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.model.toUpdateTaskRequest
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator
import ru.rshbdigital.farmhub.core.state.BaseViewModel
import ru.rshbdigital.farmhub.core.ui.model.TaskBadgeItem
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem
import ru.rshbdigital.farmhub.core.ui.model.Text
import ru.rshbdigital.farmhub.core.util.formatTo
import ru.rshbdigital.farmhub.feature.tasks.state.TasksUiState
import ru.rshbdigital.farmhub.feature.tasks.state.TasksVmState
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class TasksViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    private val tasksRepository: TasksRepository
) : BaseViewModel<TasksVmState, TasksUiState>(routeNavigator) {

    override fun getInitialVmState() = TasksVmState.getInitial()

    override fun getInitialUiState() = TasksUiState.getInitial()

    override fun mapVmStateToUiState(vmState: TasksVmState) = TasksUiState(
        tasks = vmState.tasks.map { task ->
            TaskSnippetItem(
                taskId = task.id,
                isActive = task.status in listOf(
                    Task.Status.OPEN,
                    Task.Status.MACHINE_INSPECTION,
                    Task.Status.IN_PROGRESS
                ),
                title = when (task.operation.type) {
                    OperationConverter.Type.SEEDING -> Text.Resource(string.seeding)
                    OperationConverter.Type.PROTECTION -> Text.Resource(string.protection)
                    OperationConverter.Type.HARVESTING -> Text.Resource(string.harvesting)
                    OperationConverter.Type.SOIL_PREPARATION -> Text.Resource(string.soil_preparation)
                },
                date = Text.Simple(task.commitDate?.formatTo("dd.MM.YY").orEmpty()),
                time = Text.Simple("08:00-14:00"),
                badge = when (task.status) {
                    Task.Status.UNKNOWN -> TaskBadgeItem.PendingMediumPriorityBadge
                    Task.Status.OPEN -> TaskBadgeItem.PendingMediumPriorityBadge
                    Task.Status.IN_PROGRESS -> TaskBadgeItem.InProgressBadge
                    Task.Status.DONE -> TaskBadgeItem.DoneBadge
                    Task.Status.CLOSED -> TaskBadgeItem.ClosedBadge
                    Task.Status.PAUSED -> TaskBadgeItem.PausedBadge
                    Task.Status.MACHINE_INSPECTION -> TaskBadgeItem.MachineInspectionBadge
                },
                location = Text.Simple(task.location.name),
                machine = Text.Simple(with(task.machine) { "$name $registrationNumber" }),
                trailingUnit = Text.Simple(with(task.unit) { "$name $serialNumber" }),
                plantType = Text.Simple("Озимая пшеница"),
                additionalParams = when (task.operation.type) {
                    OperationConverter.Type.SEEDING -> listOf(
                        Text.ResourceParams(
                            string.speed_with_arg,
                            listOf(task.operation.speed.roundToInt())
                        ),
                        Text.ResourceParams(
                            string.depth_with_arg,
                            listOf(task.operation.depth.roundToInt())
                        )
                    )
                    OperationConverter.Type.PROTECTION -> listOf(
                        Text.ResourceParams(
                            string.speed_with_arg,
                            listOf(task.operation.speed.roundToInt())
                        ),
                        Text.ResourceParams(
                            string.flow_rate_with_arg,
                            listOf(task.operation.flowRate.roundToInt())
                        )
                    )
                    OperationConverter.Type.HARVESTING -> listOf(
                        Text.ResourceParams(
                            string.speed_with_arg,
                            listOf(task.operation.speed.roundToInt())
                        ),
                        Text.ResourceParams(
                            string.depth_with_arg,
                            listOf(task.operation.depth.roundToInt())
                        )
                    )
                    OperationConverter.Type.SOIL_PREPARATION -> listOf(
                        Text.ResourceParams(
                            string.speed_with_arg,
                            listOf(task.operation.speed.roundToInt())
                        ),
                        Text.ResourceParams(
                            string.depth_with_arg,
                            listOf(task.operation.depth.roundToInt())
                        )
                    )
                },
                primaryButtonText = Text.Simple(
                    text = when (task.status) {
                        Task.Status.OPEN -> "Взять в работу"
                        Task.Status.IN_PROGRESS -> "Завершить работу"
                        else -> "Перевести в \"Открыто\""
                    }
                ),
                secondaryButtonText = Text.Simple("Сообщить о проблеме")
            )
        }.toImmutableList(),
        isRefreshing = vmState.isRefreshing
    )

    init {
        launchSafe {
            val tasks = tasksRepository.getTasks(page = 1)
            updateState {
                it.copy(
                    tasks = tasks.results.orEmpty()
                )
            }
        }
    }

    fun primaryButtonClick(taskSnippetItem: TaskSnippetItem) {
        val taskId = taskSnippetItem.taskId
        val task = state.tasks.firstOrNull { it.id == taskId } ?: return
        val newStatus = when (task.status) {
            Task.Status.OPEN -> Task.Status.IN_PROGRESS
            Task.Status.IN_PROGRESS -> Task.Status.DONE
            else -> Task.Status.OPEN
        }
        val newTask = task.copy(
            status = newStatus
        )
        launchSafe {
            val updatedTask = tasksRepository.updateTask(
                task = newTask,
                updateTaskRequest = newTask.toUpdateTaskRequest()
            )
            updateState { state ->
                state.copy(
                    tasks = state.tasks.map { curTask ->
                        if (curTask.id == updatedTask?.id) {
                            updatedTask
                        } else {
                            curTask
                        }
                    },
                )
            }
        }
    }

    fun refresh() = launchSafe {
        updateState {
            it.copy(
                isRefreshing = true
            )
        }
        val tasks = tasksRepository.getTasks(page = 1)
        updateState {
            it.copy(
                isRefreshing = false,
                tasks = tasks.results.orEmpty()
            )
        }
    }

    fun secondaryButtonClick(taskSnippetItem: TaskSnippetItem) {
        // TODO
    }

    override fun handleError(exception: Exception?) {
        super.handleError(exception)
        updateState {
            it.copy(
                isRefreshing = false
            )
        }
    }
}