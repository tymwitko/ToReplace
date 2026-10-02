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
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.list.TaskListViewModel
import com.tymwitko.toreplace.list.TaskViewData
import com.tymwitko.toreplace.list.toStringResource
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListItem(
  viewData: TaskViewData,
  viewModel: TaskListViewModel = koinViewModel()
) {
  val ctx = LocalContext.current
  val res = LocalResources.current

  val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
    initialValue = SwipeToDismissBoxValue.Settled,
    confirmValueChange = { targetValue ->
      if (targetValue != SwipeToDismissBoxValue.Settled) {
        viewModel.updateToDelete(viewData.task)
      }
      false
    },
    positionalThreshold = SwipeToDismissBoxDefaults.positionalThreshold
  )

  SwipeToDismissBox(
    state = swipeToDismissBoxState,
    backgroundContent = {},
    enableDismissFromEndToStart = true,
    enableDismissFromStartToEnd = true
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 4.dp)
        .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(12.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = viewData.task.name,
          color = MaterialTheme.colorScheme.onBackground,
          fontSize = 22.sp
        )
        Text(
          text = viewData.task.description,
          color = MaterialTheme.colorScheme.onBackground,
          fontSize = 16.sp
        )
        DueDaysText(viewData.dueInDays)
      }
      Button(
        onClick = {
          viewModel.resetLastTimeDone(viewData)
          Toast.makeText(
            ctx,
            res.getString(
              R.string.reminder_reset,
              viewData.task.interval.number.toString(),
              res.getQuantityString(
                viewData.task.interval.cycleType.toStringResource(),
                viewData.task.interval.number
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
}
