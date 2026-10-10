package com.tymwitko.toreplace.common

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tymwitko.toreplace.R
import com.tymwitko.toreplace.common.Consts.DONATION_URL
import com.tymwitko.toreplace.common.Consts.LOG_FILE_NAME
import com.tymwitko.toreplace.common.Consts.REPORT_ISSUE_URL
import com.tymwitko.toreplace.list.ui.TaskListScreen
import com.tymwitko.toreplace.newtask.ui.NewTaskScreen
import com.tymwitko.toreplace.settings.menu.SettingsMenuScreen
import com.tymwitko.toreplace.settings.menu.SettingsMenuViewData
import com.tymwitko.toreplace.settings.ui.UiSettingsScreen
import kotlin.time.Clock

@Composable
fun ReplaceNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  promptLauncher: ActivityResultLauncher<String>,
  startDestination: String = NavigationItem.TaskList.route
) {
  val context = LocalContext.current
  val resources = LocalResources.current
  fun handleUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(browserIntent)
  }

  @Composable
  fun SettingsListData() = listOf(
    SettingsMenuViewData(
      resources.getString(R.string.setting_item_ui),
      painterResource(R.drawable.ui_settings,),
      NavigationItem.UiSettings.route
    ),
    SettingsMenuViewData(
      resources.getString(R.string.setting_item_donate),
      painterResource(R.drawable.donate),
      NavigationItem.Donate.route
    ),
    SettingsMenuViewData(
      resources.getString(R.string.setting_item_report),
      painterResource(R.drawable.bug_report),
      NavigationItem.ReportIssue.route
    ),
    SettingsMenuViewData(
      resources.getString(R.string.setting_item_logs),
      painterResource(R.drawable.download),
      NavigationItem.DownloadLogs.route
    )
  )

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination
  ) {
    composable(NavigationItem.TaskList.route) {
      TaskListScreen(navController)
    }
    composable(NavigationItem.NewTask.route) {
      NewTaskScreen(navController)
    }
    composable(NavigationItem.Donate.route) {
      LaunchedEffect(Unit) {
        handleUrl(DONATION_URL)
      }
      navController.navigate(NavigationItem.Settings.route)
    }
    composable(NavigationItem.ReportIssue.route) {
      LaunchedEffect(Unit) {
        handleUrl(REPORT_ISSUE_URL)
      }
      navController.navigate(NavigationItem.Settings.route)
    }
    composable(NavigationItem.Settings.route) {
      SettingsMenuScreen(
        navController = navController,
        entries = SettingsListData()
      )
    }
    composable(NavigationItem.UiSettings.route) {
      UiSettingsScreen()
    }
    composable(NavigationItem.DownloadLogs.route) {
      LaunchedEffect(Unit) {
        promptLauncher.launch("${LOG_FILE_NAME}_${Clock.System.now().epochSeconds}.txt")
      }
      navController.navigate(NavigationItem.Settings.route)
    }
  }
}

enum class Screen {
  TASK_LIST,
  NEW_TASK,
  DONATE,
  ISSUE,
  UI_SETTINGS,
  SETTINGS,
  DOWNLOAD_LOGS
}

sealed class NavigationItem(val route: String) {
  object TaskList : NavigationItem(Screen.TASK_LIST.name)
  object NewTask : NavigationItem(Screen.NEW_TASK.name)
  object Donate : NavigationItem(Screen.DONATE.name)
  object ReportIssue : NavigationItem(Screen.ISSUE.name)
  object UiSettings : NavigationItem(Screen.UI_SETTINGS.name)
  object Settings : NavigationItem(Screen.SETTINGS.name)
  object DownloadLogs : NavigationItem(Screen.DOWNLOAD_LOGS.name)
}
