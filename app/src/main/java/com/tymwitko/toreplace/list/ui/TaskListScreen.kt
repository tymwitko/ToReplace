package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tymwitko.toreplace.list.TaskListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
  viewModel: TaskListViewModel = koinViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  Box(
    modifier = Modifier.statusBarsPadding().navigationBarsPadding()
  ) {
    when (val state = uiState) {
      is TaskListUiState.Success -> {
        TaskList(state.list)
      }
      TaskListUiState.EmptyList -> {}
      is TaskListUiState.Error -> {}
      TaskListUiState.Loading -> {}
      TaskListUiState.MissingPermissions -> {}
    }
  }
}
