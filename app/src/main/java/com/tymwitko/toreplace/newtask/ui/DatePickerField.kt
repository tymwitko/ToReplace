package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.tymwitko.toreplace.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DatePickerField(modifier: Modifier = Modifier) {
  var selectedDate by remember { mutableStateOf<Long?>(null) }
  var showModal by remember { mutableStateOf(false) }

  fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
  }
  OutlinedTextField(
    value = selectedDate?.let { convertMillisToDate(it) } ?: "",
    onValueChange = { },
    label = { Text(stringResource(R.string.start_date)) },
    placeholder = { Text("DD/MM/YYYY") },
    modifier = modifier
      .fillMaxWidth()
      .pointerInput(selectedDate) {
        awaitEachGesture {
          awaitFirstDown(pass = PointerEventPass.Initial)
          waitForUpOrCancellation(pass = PointerEventPass.Initial)?.let {
            showModal = true
          }
        }
      }
  )

  if (showModal) {
    DatePickerModal(
      onDateSelected = { selectedDate = it },
      onDismiss = { showModal = false }
    )
  }
}
