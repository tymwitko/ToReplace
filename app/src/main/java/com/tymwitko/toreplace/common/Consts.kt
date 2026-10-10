package com.tymwitko.toreplace.common

import com.tymwitko.toreplace.BuildConfig

object Consts {
  const val ERROR_ISSUE_URL = "https://github.com/tymwitko/toreplace/issues/new?title=I encountered " +
    "the error screen!&body=**Describe how and when it happened**%0A%0A%0A**Paste the error log " +
    "below (it's in your clipboard)**%0A%0A%0A**App version**%0A${BuildConfig.VERSION_NAME}%0A%0A" +
    "**Enter your device model and OS version**%0A%0A"
  const val DONATION_URL = "https://buymeacoffee.com/tymwitko"
  const val REPORT_ISSUE_URL = "https://github.com/tymwitko/recents/issues/new?body=**Describe the " +
    "issue**%0A%0A%0A**Expected outcome**%0A%0A%0A**App version**%0A${BuildConfig.VERSION_NAME}" +
    "%0A%0A**Enter your device model and OS version**%0A%0A"
  const val SHARED_PREFS_KEY = "com.tymwitko.toreplace.UI_PREFS"
  const val FONT_SIZE_ALIAS = "FONT_SIZE"
  const val MARGIN_SIZE_ALIAS = "MARGIN_SIZE"
  const val IS_REVERSED_ORDER_ALIAS = "REVERSED_ORDER"
  const val DEFAULT_FONT_SIZE = 12
  const val DEFAULT_MARGIN_SIZE = 16
  const val LOG_FILE_NAME = "recents_log.txt"
  const val EXTRACT_LOGCAT_COMMAND = "logcat -d"
}
