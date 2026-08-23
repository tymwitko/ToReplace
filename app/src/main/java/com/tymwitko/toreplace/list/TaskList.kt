package com.tymwitko.toreplace.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>) {
  LazyColumn {
    items(tasks, key = { it.name }) {
      TaskListItem(
        it.name,
        it.cycleLength,
        it.cycleTask,
        it.dueInDays
      )
    }
  }
}
