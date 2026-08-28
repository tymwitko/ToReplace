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
        is Result.Success -> uiState.emit(TaskListUiState.Success(result.data))
        is Result.Failure -> {
          when (result.error) {
            TaskListError.Empty -> uiState.emit(TaskListUiState.Error("List empty"))
            is TaskListError.Exception ->
              uiState.emit(TaskListUiState.Error(result.error.message))
          }
        }
      }
    }
  }
}
