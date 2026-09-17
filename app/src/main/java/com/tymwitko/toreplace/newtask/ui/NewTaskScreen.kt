package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.ui.clearFocusOnKeyboardDismiss
import com.tymwitko.toreplace.list.CycleType
import com.tymwitko.toreplace.newtask.NewTaskViewModel
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewTaskScreen(
  viewModel: NewTaskViewModel = koinViewModel()
) {
  val titleFieldState: TextFieldState = rememberTextFieldState()
  val descriptionFieldState: TextFieldState = rememberTextFieldState()
  val numberFieldState = rememberTextFieldState()
  var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
  var showCalendar by remember { mutableStateOf(false) }
  var showDropdown by remember { mutableStateOf(false) }
  var selectedCycleType by remember { mutableStateOf<CycleType?>(null) }
  Column(
    modifier = Modifier
      .navigationBarsPadding()
      .statusBarsPadding()
      .fillMaxSize()
      .padding(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth(),
      text = stringResource(R.string.add_task)
    )
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .clearFocusOnKeyboardDismiss(),
      state = titleFieldState,
      label = { Text(stringResource(R.string.title)) }
    )
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .clearFocusOnKeyboardDismiss(),
      state = descriptionFieldState,
      label = { Text(stringResource(R.string.description)) }
    )
    OutlinedTextField(
      value = selectedDate?.toString().orEmpty(),
      onValueChange = { },
      label = { Text(stringResource(R.string.last_done)) },
      modifier = Modifier
        .fillMaxWidth()
        .pointerInput(selectedDate) {
          awaitEachGesture {
            awaitFirstDown(pass = PointerEventPass.Initial)
            waitForUpOrCancellation(pass = PointerEventPass.Initial)?.let {
              showCalendar = true
            }
          }
        }
    )

    if (showCalendar) {
      DatePickerModal(
        onDateSelected = { selectedDate = it },
        onDismiss = { showCalendar = false }
      )
    }

    Row(
      modifier = Modifier
        .padding(vertical = 12.dp)
        .height(IntrinsicSize.Max),
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      TextField(
        modifier = Modifier
          .weight(1f),
        state = numberFieldState,
        label = { Text(stringResource(R.string.interval_number)) },
        inputTransformation = DigitOnlyInputTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
      ) {
        Button(
          modifier = Modifier.fillMaxSize(),
          onClick = {
            showDropdown = !showDropdown
          },
          shape = RectangleShape,
          colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
          )
        ) {
          Text(
            text = selectedCycleType?.name ?: "Select interval")
        }
        DropdownMenu(
          expanded = showDropdown,
          onDismissRequest = {
            showDropdown = false
          }
        ) {
          CycleType.entries.forEach {
            IntervalDropDownMenuItem(
              runCatching { numberFieldState.text.toString().toInt() }.getOrDefault(1) ,
              it
            ) {
              selectedCycleType = it
              showDropdown = false
            }
          }
        }
      }
    }

    Button(
      enabled = titleFieldState.text.isNotBlank() &&
        descriptionFieldState.text.isNotBlank() &&
        numberFieldState.text.isNotBlank() &&
        selectedDate != null &&
        selectedCycleType != null,
      shape = RectangleShape,
      colors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary,
        disabledContentColor = MaterialTheme.colorScheme.onSecondary
      ),
      onClick = {
        viewModel.submit(
          titleFieldState.text.toString(),
          descriptionFieldState.text.toString(),
          numberFieldState.text.toString().toInt(),
          selectedCycleType!!, // todo: better handling
          selectedDate!!
        )
      }
    ) {
      Text(stringResource(R.string.ok))
    }
  }
}

