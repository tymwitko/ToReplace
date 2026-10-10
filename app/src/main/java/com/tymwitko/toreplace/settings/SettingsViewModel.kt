package com.tymwitko.toreplace.settings

import androidx.lifecycle.ViewModel

class SettingsViewModel(private val settingsHolder: SettingsHolder) : ViewModel() {

  fun getFontSize() = settingsHolder.getFontSize()
}
