package com.tymwitko.toreplace.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tymwitko.toreplace.common.ui.theme.ToReplaceTheme
import com.tymwitko.toreplace.list.ui.TaskListScreen

class ListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ToReplaceTheme {
        TaskListScreen()
      }
    }
  }
}
