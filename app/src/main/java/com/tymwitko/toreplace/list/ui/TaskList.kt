package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.tymwitko.toreplace.list.Task

@Composable
fun TaskList(tasks: List<Pair<Task, Int>>) {
  LazyColumn {
    items(tasks, key = { it.first.id }) {
      TaskListItem(it.first, it.second)
    }
  }
}
