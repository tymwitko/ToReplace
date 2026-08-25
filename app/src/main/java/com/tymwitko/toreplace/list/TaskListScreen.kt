package com.tymwitko.toreplace.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
  viewModel: TaskListViewModel = koinViewModel()
) {
  Box(
    modifier = Modifier.statusBarsPadding().navigationBarsPadding()
  ) {
    TaskList(
      listOf(
        Task("Replace the water filter", 30, CycleType.DAYS, viewModel.getDueDays(0)),
        Task("Clean the dishwasher", 6, CycleType.MONTHS, viewModel.getDueDays(1)),
        Task("Clean the washing machine", 6, CycleType.MONTHS, viewModel.getDueDays(2)),
        Task("Check tire pressure", 1, CycleType.YEARS, viewModel.getDueDays(3))
      )
    )
  }
}
