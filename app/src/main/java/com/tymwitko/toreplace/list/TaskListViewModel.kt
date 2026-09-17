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
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate

class TaskListViewModel(
  private val fetchTasksUseCase: FetchTasksUseCase,
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
          val mapWithDays = result.data.map {
            it to getDaysLeft(it.startDate, it.interval)
          }
          uiState.emit(TaskListUiState.Success(mapWithDays))
        }
        is Result.Failure -> {
          when (result.error) {
            TaskListError.Empty -> uiState.emit(TaskListUiState.EmptyList)
            is TaskListError.Exception ->
              uiState.emit(TaskListUiState.Error(result.error.message))
          }
        }
      }
    }
  }

  fun getDaysLeft(startDate: LocalDate, interval: Interval): Int {
    val today = LocalDate.now()
    return when (interval.cycleType) {
      CycleType.DAYS -> {
        interval.number - (today.toEpochDay() - startDate.toEpochDay()).mod(interval.number)
      }
      CycleType.WEEKS -> {
        interval.number * 7 - (today.toEpochDay() - startDate.toEpochDay()).mod(interval.number * 7)
      }
      CycleType.MONTHS -> {
        val diff = startDate.dayOfMonth - today.dayOfMonth
        when {
          diff > 0 -> diff
          diff < 0 -> today.lengthOfMonth() - today.dayOfMonth + startDate.dayOfMonth
          else -> today.lengthOfMonth()
        }
      }
      CycleType.YEARS -> {
        val sameDateThisYear = LocalDate.of(today.year, startDate.month, startDate.dayOfMonth)
        val diff = Duration.between(today, sameDateThisYear).toDays().toInt()
        if (diff > 0) diff else {
          val sameDateNextYear = LocalDate.of(today.year + 1, startDate.month, startDate.dayOfMonth)
          Duration.between(today, sameDateNextYear).toDays().toInt()
        }
      }
    }
  }
}
