package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tymwitko.toreplace.R

@Composable
fun ConfirmDeleteDialog(
  onConfirm: () -> Unit,
  onDismiss: () -> Unit
) {
  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(16.dp),
      shape = RoundedCornerShape(16.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          modifier = Modifier.padding(12.dp),
          text = stringResource(R.string.delete)
        )
        TextButton(
          modifier = Modifier.padding(12.dp),
          onClick = {
            onConfirm()
            onDismiss()
          }
        ) {
          Text(stringResource(R.string.ye))
        }
        TextButton(
          modifier = Modifier.padding(12.dp),
          onClick = {
            onDismiss()
          }
        ) {
          Text(stringResource(R.string.nah))
        }
      }
    }
  }
}
