package com.tymwitko.toreplace.list.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.ui.theme.LocalAppColors

@Composable
fun DueDaysText(daysLeft: Int, fontSize: TextUnit) = when {
  daysLeft > 0 ->
    Text(
      text = stringResource(
        R.string.due_in,
        daysLeft,
        pluralStringResource(R.plurals.day, daysLeft).lowercase()
      ),
      color = MaterialTheme.colorScheme.onBackground,
      fontSize = fontSize
    )

  daysLeft == 0 -> Text(
    text = stringResource(R.string.due_today),
    color = LocalAppColors.current.warning,
    fontSize = fontSize
  )

  else -> Text(
    text = stringResource(
      R.string.late_by,
      -daysLeft,
      pluralStringResource(R.plurals.day, -daysLeft).lowercase()
    ),
    color = MaterialTheme.colorScheme.error,
    fontSize = fontSize
  )
}
