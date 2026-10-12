package com.tymwitko.toreplace.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.list.SortingType
import com.tymwitko.toreplace.list.toStringResource

@Composable
fun SortingDropDownMenuItem(sortingType: SortingType, selected: Boolean, onClick: () -> Unit) {
  Row(
    Modifier
      .fillMaxSize()
      .padding(16.dp)
      .clickable(
        onClick = onClick
      )
  ) {
    RadioButton(
      selected,
      onClick = onClick,
    )
    Text(
      text = stringResource(sortingType.toStringResource())
    )
  }
}
