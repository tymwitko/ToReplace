package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.list.CycleType

@Composable
fun IntervalDropDownMenuItem(cycleType: CycleType, onClick: () -> Unit) {
  Box(
    Modifier
      .border(
        width = 2.dp,
        color = MaterialTheme.colorScheme.onBackground
      )
      .padding(16.dp)
      .clickable(
        onClick = onClick
      )
  ) {
    Text(
      text = stringResource(
        when (cycleType) { // todo: proper plurals
          CycleType.DAYS -> R.string.days
          CycleType.WEEKS -> R.string.weeks
          CycleType.MONTHS -> R.string.months
          CycleType.YEARS -> R.string.years
        }
      )
    )
  }
}
