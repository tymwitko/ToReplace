package com.tymwitko.toreplace.settings.menu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tymwitko.toreplace.BuildConfig
import com.tymwitko.toreplace.common.NavigationItem
import com.tymwitko.toreplace.settings.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsMenuScreen(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  entries: List<SettingsMenuViewData>,
  viewModel: SettingsViewModel = koinViewModel()
) {
  BackHandler {
    navController.navigate(NavigationItem.TaskList.route)
  }
  Column(
    modifier = modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .weight(1f)
    ) {
      SettingsList(
        navController = navController,
        entries = entries,
        fontSize = viewModel.getFontSize()
      )
    }
    Text(
      text = "v${BuildConfig.VERSION_NAME}",
      color = MaterialTheme.colorScheme.onBackground,
      fontSize = 12.sp
    )
  }
}
