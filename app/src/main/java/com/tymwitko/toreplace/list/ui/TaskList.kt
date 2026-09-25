package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.tymwitko.toreplace.list.TaskViewData

@Composable
fun TaskList(tasks: List<TaskViewData>) {
  LazyColumn {
    items(tasks, key = { it.task.id }) {
      TaskListItem(it)
    }
  }
}
