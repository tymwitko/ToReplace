package com.tymwitko.toreplace.list.ui

import com.tymwitko.toreplace.list.TaskViewData

sealed interface TaskListUiState {
  object Loading : TaskListUiState
  object EmptyList : TaskListUiState
  data class Success(
    val list: List<TaskViewData>,
  ) : TaskListUiState
  data class Error(val message: String) : TaskListUiState
}
