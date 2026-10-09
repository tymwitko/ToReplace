package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.tymwitko.toreplace.list.TaskViewData

@Composable
fun TaskList(
  tasks: List<TaskViewData>,
  fontSize: TextUnit,
  itemSize: Dp
) {
  LazyColumn {
    items(tasks, key = { it.task.id to it.task.name }) {
      TaskListItem(it, fontSize, itemSize)
    }
  }
}
