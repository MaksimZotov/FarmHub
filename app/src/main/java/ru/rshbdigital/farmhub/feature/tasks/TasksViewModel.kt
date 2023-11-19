package ru.rshbdigital.farmhub.feature.tasks

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import ru.rshbdigital.farmhub.R.string
import ru.rshbdigital.farmhub.client.tasks.TasksRepository
import ru.rshbdigital.farmhub.core.model.Operation
import ru.rshbdigital.farmhub.core.model.Task
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator
import ru.rshbdigital.farmhub.core.state.BaseViewModel
import ru.rshbdigital.farmhub.core.ui.model.TaskBadgeItem
import ru.rshbdigital.farmhub.core.ui.model.TaskSnippetItem
import ru.rshbdigital.farmhub.core.ui.model.Text
import ru.rshbdigital.farmhub.core.util.formatTo
import ru.rshbdigital.farmhub.feature.tasks.state.TasksUiState
import ru.rshbdigital.farmhub.feature.tasks.state.TasksVmState
import javax.inject.Inject

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
                title = when (task.operation) {
                    is Operation.Seeding -> Text.Resource(string.seeding)
                    is Operation.Protection -> Text.Resource(string.protection)
                    is Operation.Harvesting -> Text.Resource(string.harvesting)
                    is Operation.SoilPreparation -> Text.Resource(string.soil_preparation)
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
                machine = Text.Simple(task.machine.name),
                trailingUnit = Text.Simple(task.unit.name),
                plantType = Text.Simple("Озимая пшеница"),
                additionalParams = when (task.operation) {
                    is Operation.Seeding -> listOf(
                        Text.Simple(task.operation.speed.toString()),
                        Text.Simple(task.operation.depth.toString())
                    )
                    is Operation.Protection -> listOf(
                        Text.Simple(task.operation.speed.toString()),
                        Text.Simple(task.operation.flowRate.toString())
                    )
                    is Operation.Harvesting -> listOf(
                        Text.Simple(task.operation.speed.toString()),
                        Text.Simple(task.operation.depth.toString())
                    )
                    is Operation.SoilPreparation -> listOf(
                        Text.Simple(task.operation.speed.toString()),
                        Text.Simple(task.operation.depth.toString())
                    )
                },
                primaryButtonText = Text.Simple("Завершить работу"),
                secondaryButtonText = Text.Simple("Сообщить о проблеме")
            )
        }.toImmutableList(),
        isProgress = vmState.isProgress
    )

    init {
        launchSafe {
            val tasks = tasksRepository.getTasks(page = 1)
            updateState {
                it.copy(
                    tasks = tasks.results.orEmpty(),
                    isProgress = false
                )
            }
        }
    }

    fun primaryButtonClick(taskSnippetItem: TaskSnippetItem) {

    }

    fun secondaryButtonClick(taskSnippetItem: TaskSnippetItem) {

    }
}