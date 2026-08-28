package com.tymwitko.toreplace.list.ui

import com.tymwitko.toreplace.list.Task

sealed interface TaskListUiState {
  object MissingPermissions : TaskListUiState
  object Loading : TaskListUiState
  object EmptyList : TaskListUiState
  data class Success(
    val list: List<Task>,
  ) : TaskListUiState
  data class Error(val message: String) : TaskListUiState
}
