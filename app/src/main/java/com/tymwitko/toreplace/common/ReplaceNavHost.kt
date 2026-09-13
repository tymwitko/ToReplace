package com.tymwitko.toreplace.common

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tymwitko.toreplace.common.Consts.DONATION_URL
import com.tymwitko.toreplace.common.Consts.REPORT_ISSUE_URL
import com.tymwitko.toreplace.list.ui.TaskListScreen
import com.tymwitko.toreplace.newtask.ui.NewTaskScreen

@Composable
fun ReplaceNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: String = NavigationItem.TaskList.route
) {
  val context = LocalContext.current
  fun handleUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(browserIntent)
  }

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination
  ) {
    composable(NavigationItem.TaskList.route) {
      TaskListScreen(navController)
    }
    composable(NavigationItem.NewTask.route) {
      NewTaskScreen()
    }
    composable(NavigationItem.Donate.route) {
      LaunchedEffect(Unit) {
        handleUrl(DONATION_URL)
      }
    }
    composable(NavigationItem.ReportIssue.route) {
      LaunchedEffect(Unit) {
        handleUrl(REPORT_ISSUE_URL)
      }
    }
  }
}

enum class Screen {
  TASK_LIST,
  NEW_TASK,
  DONATE,
  ISSUE
}

sealed class NavigationItem(val route: String) {
  object TaskList : NavigationItem(Screen.TASK_LIST.name)
  object NewTask : NavigationItem(Screen.NEW_TASK.name)
  object Donate : NavigationItem(Screen.DONATE.name)
  object ReportIssue : NavigationItem(Screen.ISSUE.name)
}
