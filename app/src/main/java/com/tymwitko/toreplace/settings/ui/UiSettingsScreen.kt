package com.tymwitko.toreplace.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.list.CycleType
import com.tymwitko.toreplace.list.Interval
import com.tymwitko.toreplace.list.Task
import com.tymwitko.toreplace.list.TaskViewData
import com.tymwitko.toreplace.list.ui.TaskListItem
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun UiSettingsScreen(
  viewModel: UiSettingsViewModel = koinViewModel()
) {
  var fontSliderPosition by rememberSaveable { mutableFloatStateOf(viewModel.getFontSize().value) }
  var marginSliderPosition by rememberSaveable {
    mutableFloatStateOf(viewModel.getMarginSize().value)
  }

  Column(
    modifier = Modifier
      .navigationBarsPadding()
      .statusBarsPadding()
      .verticalScroll(rememberScrollState())
      .padding(vertical = 24.dp)
  ) {
    SizeSlider(
      sliderPosition = fontSliderPosition,
      label = stringResource(R.string.set_font_size),
      valueRange = 3F..24F
    ) {
      fontSliderPosition = it
      viewModel.saveFontSize(it)
    }
    SizeSlider(
      sliderPosition = marginSliderPosition,
      label = stringResource(R.string.set_margin_size),
      valueRange = 2F..32F
    ) {
      marginSliderPosition = it
      viewModel.saveMarginSize(it)
    }
    Text(
      modifier = Modifier.padding(24.dp),
      text = stringResource(R.string.preview),
      color = MaterialTheme.colorScheme.onBackground
    )
    TaskListItem(
      viewData = TaskViewData(
        task = Task(
          id = null,
          name = "Example task",
          description = "Example description (kinda long)",
          interval = Interval(7, CycleType.MONTHS),
          lastTimeDone = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        ),
        dueInDays = 420
      ),
      fontSize = fontSliderPosition.sp,
      itemSize = marginSliderPosition.dp
    )
  }
}
