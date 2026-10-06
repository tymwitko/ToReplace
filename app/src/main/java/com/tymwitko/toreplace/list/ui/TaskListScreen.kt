package com.tymwitko.toreplace.list.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.Screen
import com.tymwitko.toreplace.common.ui.ErrorScreen
import com.tymwitko.toreplace.common.ui.PulseAnimation
import com.tymwitko.toreplace.common.ui.toImageBitmap
import com.tymwitko.toreplace.list.TaskListViewModel
import com.tymwitko.toreplace.newtask.ui.ConfirmDeleteDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
  navController: NavHostController,
  viewModel: TaskListViewModel = koinViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val density = LocalDensity.current
  val layoutDirection = LocalLayoutDirection.current
  val clipBoardManager =
    LocalContext.current.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

  LaunchedEffect(Unit) {
    viewModel.fetchTasks()
  }

  Column(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .fillMaxSize()
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        modifier = Modifier,
        text = "ToReplace",
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 24.sp
      )
      Row(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        horizontalArrangement = Arrangement.End
      ) {
        IconButton(
          onClick = {},
        ) {
          Image(
            painter = painterResource(R.drawable.settings),
            contentDescription = null
          )
        }
        IconButton(
          onClick = {},
        ) {
          Image(
            painter = painterResource(R.drawable.sort),
            contentDescription = null
          )
        }
      }
    }
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      when (val state = uiState) {
        is TaskListUiState.Success -> {
          TaskList(state.list)

          state.taskToDelete?.let {
            ConfirmDeleteDialog(
              onConfirm = {
                viewModel.deleteTask(it)
              },
              {
                viewModel.updateToDelete(null)
              }
            )
          }
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
          navController.navigate(Screen.NEW_TASK.name)
        },
        content = {
          painterResource(R.drawable.add)
            .toImageBitmap(density, layoutDirection)
            .let { Icon(it, null) }
        }
      )
    }
  }
}
