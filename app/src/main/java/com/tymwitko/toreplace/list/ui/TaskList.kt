package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.tymwitko.toreplace.list.Task

@Composable
fun TaskList(tasks: List<Task>) {
  LazyColumn {
    items(tasks, key = { it.name }) {
      TaskListItem(
        it.name,
        it.interval.number,
        it.interval.cycleType,
        it.startDate.dayOfMonth
      )
    }
  }
}
