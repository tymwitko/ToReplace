package com.tymwitko.toreplace.list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun TaskListItem(
  taskName: String,
  cycleLength: Int,
  cycleType: CycleType,
  dueInDays: Int
) {
  val ctx = LocalContext.current
  Row {
    Column {
      Text(taskName)
      Text("Due in $dueInDays days")
    }
    Button(
      onClick = {
        Toast.makeText(
          ctx,
          "Reminder reset to $cycleLength ${cycleType.name.lowercase()}",
          Toast.LENGTH_SHORT
        ).show()
      }
    ) {
      Text("DID IT")
    }
  }
}
