package com.tymwitko.toreplace.newtask.ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tymwitko.toreplace.R

@Composable
fun DatePickerModal(
  onDateSelected: (Long?) -> Unit,
  onDismiss: () -> Unit
) {
  val datePickerState = rememberDatePickerState()

  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(onClick = {
        onDateSelected(datePickerState.selectedDateMillis)
        onDismiss()
      }) {
        Text(stringResource(R.string.ok))
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text(stringResource(R.string.cancel))
      }
    }
  ) {
    DatePicker(state = datePickerState)
  }
}
