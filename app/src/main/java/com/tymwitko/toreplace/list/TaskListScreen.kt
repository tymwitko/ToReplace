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
        Task(0, "Replace the water filter", "", Interval(30, CycleType.DAYS), viewModel.getStartDate(0)),
        Task(1, "Clean the dishwasher", "", Interval(6, CycleType.MONTHS), viewModel.getStartDate(1)),
        Task(2, "Clean the washing machine", "", Interval(6, CycleType.MONTHS), viewModel.getStartDate(2)),
        Task(3, "Check tire pressure", "", Interval(1, CycleType.YEARS), viewModel.getStartDate(3))
      )
    )
  }
}
