package com.tymwitko.toreplace.list.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.tymwitko.toreplace.R

@Composable
fun DueDaysText(daysLeft: Int) = when {
  daysLeft > 0 ->
    Text(
      text = stringResource(
        R.string.due_in,
        daysLeft,
        pluralStringResource(R.plurals.day, daysLeft).lowercase()
      ),
      color = MaterialTheme.colorScheme.onBackground
    )

  daysLeft == 0 -> Text(
    text = stringResource(R.string.due_today),
    color = MaterialTheme.colorScheme.error
  )

  else -> Text(
    text = stringResource(
      R.string.late_by,
      -daysLeft,
      pluralStringResource(R.plurals.day, -daysLeft).lowercase()
    ),
    color = MaterialTheme.colorScheme.errorContainer
  )
}
