package com.tymwitko.toreplace.list.ui

import com.tymwitko.toreplace.list.Task

sealed interface TaskListUiState {
  object Loading : TaskListUiState
  object EmptyList : TaskListUiState
  data class Success(
    val list: List<Pair<Task, Int>>,
  ) : TaskListUiState
  data class Error(val message: String) : TaskListUiState
}
