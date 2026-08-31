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
}
