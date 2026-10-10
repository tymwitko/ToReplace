package com.tymwitko.toreplace.list

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.tymwitko.toreplace.common.Consts.EXTRACT_LOGCAT_COMMAND
import com.tymwitko.toreplace.common.ReplaceNavHost
import com.tymwitko.toreplace.common.ui.theme.ToReplaceTheme

class ListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val logFileLauncher =
      registerForActivityResult(ActivityResultContracts.CreateDocument("text/plain")) { uri: Uri? ->
        if (uri != null) saveLogsToUri(contentResolver, uri)
      }
    enableEdgeToEdge()
    setContent {
      ToReplaceTheme {
        ReplaceNavHost(
          navController = rememberNavController(),
          promptLauncher = logFileLauncher
        )
      }
    }
  }

  private fun saveLogsToUri(contentResolver: ContentResolver, uri: Uri) {
    try {
      contentResolver.openOutputStream(uri)?.use { outStream ->
        val process = Runtime.getRuntime().exec(EXTRACT_LOGCAT_COMMAND)
        process.inputStream.use { input ->
          input.copyTo(outStream)
        }
        process.waitFor()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}
