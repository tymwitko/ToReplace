package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.tymwitko.toreplace.list.CycleType
import com.tymwitko.toreplace.list.toStringResource

@Composable
fun IntervalDropDownMenuItem(cycleNumber: Int, cycleType: CycleType, onClick: () -> Unit) {
  Box(
    Modifier
      .fillMaxSize()
      .padding(16.dp)
      .clickable(
        onClick = onClick
      )
  ) {
    Text(
      text = pluralStringResource(cycleType.toStringResource(), cycleNumber)
    )
  }
}
