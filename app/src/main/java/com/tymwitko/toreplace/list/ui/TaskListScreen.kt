package com.tymwitko.toreplace.list.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.ui.ErrorScreen
import com.tymwitko.toreplace.common.ui.PulseAnimation
import com.tymwitko.toreplace.common.ui.toImageBitmap
import com.tymwitko.toreplace.list.TaskListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
  viewModel: TaskListViewModel = koinViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val density = LocalDensity.current
  val layoutDirection = LocalLayoutDirection.current
  val clipBoardManager =
    LocalContext.current.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  Box(
    modifier = Modifier.statusBarsPadding().navigationBarsPadding().fillMaxSize()
  ) {
    when (val state = uiState) {
      is TaskListUiState.Success -> {
        TaskList(state.list)
      }
      TaskListUiState.EmptyList -> {}
      is TaskListUiState.Error -> {
        ErrorScreen(state.message, viewModel::fetchTasks) {
          clipBoardManager.setPrimaryClip(ClipData.newPlainText("", state.message))
        }
      }
      TaskListUiState.Loading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          PulseAnimation()
        }
      }
    }
    FloatingActionButton(
      modifier = Modifier
        .padding(10.dp)
        .navigationBarsPadding()
        .align(Alignment.BottomEnd),
      onClick = {

      },
      content = {
        painterResource(R.drawable.add)
          .toImageBitmap(density, layoutDirection)
          .let { Icon(it, null) }
      }
    )
  }
}
