package com.tymwitko.toreplace.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.ui.TaskListUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class TaskListViewModel(
  private val fetchTasksUseCase: FetchTasksUseCase,
  private val updateTaskUseCase: UpdateTaskUseCase,
  private val deleteTaskUseCase: DeleteTaskUseCase,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

  init {
    fetchTasks()
  }

  val uiState: StateFlow<TaskListUiState>
    field = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)

  fun fetchTasks() {
    viewModelScope.launch(dispatcher) {
      if (uiState.value !is TaskListUiState.Success) uiState.emit(TaskListUiState.Loading)
      when (val result = fetchTasksUseCase()) {
        is Result.Success -> {
          val tasksWithDays = result.data.map {
            TaskViewData(it, getDaysLeft(it.lastTimeDone, it.interval))
          }
          uiState.emit(TaskListUiState.Success(tasksWithDays))
        }

        is Result.Failure -> handleError(result.error)
      }
    }
  }

  fun resetLastTimeDone(viewData: TaskViewData) {
    viewModelScope.launch(dispatcher) {
      if (uiState.value is TaskListUiState.Success) {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        when (val result = updateTaskUseCase(viewData.task, today)) {
          is Result.Success -> updateOnReset(viewData, today)
          is Result.Failure -> handleError(result.error)
        }
      }
    }
  }

  fun updateOnReset(task: TaskViewData, today: LocalDate) {
    uiState.update { old ->
      (old as? TaskListUiState.Success)?.let { succ ->
        old.copy(
          list = succ.list.map {
            if (it == task) task.copy(
              dueInDays = getDaysLeft(today, task.task.interval)
            ) else it
          }
        )
      } ?: old
    }
  }

  suspend fun handleError(error: TaskListError) {
    when (error) {
      TaskListError.Empty -> uiState.emit(TaskListUiState.EmptyList)
      is TaskListError.Exception ->
        uiState.emit(TaskListUiState.Error(error.message))
    }
  }

  fun getDaysLeft(lastDoneDate: LocalDate, interval: Interval): Int {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return when (interval.cycleType) {
      CycleType.DAYS -> {
        interval.number - (today.toEpochDays() - lastDoneDate.toEpochDays())
      }
      CycleType.WEEKS -> {
        interval.number * 7 - (today.toEpochDays() - lastDoneDate.toEpochDays())
      }
      CycleType.MONTHS -> {
        lastDoneDate.plus(interval.number.toLong(), DateTimeUnit.MONTH)
          .daysSince(today)
      }
      CycleType.YEARS -> {
        lastDoneDate.plus(interval.number.toLong(), DateTimeUnit.YEAR)
          .daysSince(today)
      }
    }
  }

  fun deleteTask(task: Task) {
    viewModelScope.launch(dispatcher) {
      (deleteTaskUseCase(task) as? Result.Success)?.let {
        uiState.update {
          (it as? TaskListUiState.Success)?.let { succ ->
            val oldList = succ.list
            succ.copy(
              list = oldList.filter { it.task != task }
            )
          } ?: it
        }
      }
    }
  }
}

fun LocalDate.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDate {
  val timeZone = TimeZone.currentSystemDefault()
  return atStartOfDayIn(timeZone)
    .plus(value, unit)
    .toLocalDateTime(timeZone)
    .date
}

fun LocalDate.daysSince(other: LocalDate): Int {
  val timeZone = TimeZone.currentSystemDefault()
  return atStartOfDayIn(timeZone)
    .minus(other.atStartOfDayIn(timeZone))
    .inWholeDays
    .toInt()
}

