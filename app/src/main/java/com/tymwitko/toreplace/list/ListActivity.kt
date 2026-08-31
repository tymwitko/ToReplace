package com.tymwitko.toreplace.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.tymwitko.toreplace.common.NavHost
import com.tymwitko.toreplace.common.ui.theme.ToReplaceTheme

class ListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ToReplaceTheme {
        NavHost(
          navController = rememberNavController()
        )
      }
    }
  }
}
