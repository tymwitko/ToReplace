package com.tymwitko.toreplace.list.ui

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.list.Task
import com.tymwitko.toreplace.list.TaskListViewModel
import com.tymwitko.toreplace.list.toStringResource
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListItem(
  task: Task,
  dueInDays: Int,
  viewModel: TaskListViewModel = koinViewModel()
) {
  val ctx = LocalContext.current
  val res = LocalResources.current

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(12.dp)
      .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(12.dp))
      .padding(12.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Column {
      Text(
        text = task.name,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = res.getString(
          R.string.due_in,
          dueInDays.toString(),
          res.getQuantityString(R.plurals.day, dueInDays)
        ),
        color = MaterialTheme.colorScheme.onBackground
      )
    }
    Button(
      onClick = {
        viewModel.resetLastTimeDone(task)
        Toast.makeText(
          ctx,
          res.getString(
            R.string.reminder_reset,
            task.interval.number.toString(),
            res.getQuantityString(
              task.interval.cycleType.toStringResource(),
              task.interval.number
            )
          ),
          Toast.LENGTH_SHORT
        ).show()
      }
    ) {
      Text(
        text = stringResource(R.string.done).uppercase()
      )
    }
  }
}
