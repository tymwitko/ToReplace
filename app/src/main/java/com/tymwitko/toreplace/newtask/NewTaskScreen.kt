package com.tymwitko.toreplace.newtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.ui.clearFocusOnKeyboardDismiss

@Composable
fun NewTaskScreen() {
  val titleFieldState: TextFieldState = rememberTextFieldState()
  val descriptionFieldState: TextFieldState = rememberTextFieldState()
  Column(
    modifier = Modifier
      .navigationBarsPadding()
      .statusBarsPadding()
      .fillMaxSize()
  ) {
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clearFocusOnKeyboardDismiss(),
      state = titleFieldState,
      placeholder = { Text(stringResource(R.string.title)) }
    )
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clearFocusOnKeyboardDismiss(),
      state = descriptionFieldState,
      placeholder = { Text(stringResource(R.string.description)) }
    )
  }
}
