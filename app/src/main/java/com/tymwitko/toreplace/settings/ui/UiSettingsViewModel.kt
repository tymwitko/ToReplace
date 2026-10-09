package com.tymwitko.toreplace.settings.ui

import androidx.lifecycle.ViewModel
import com.tymwitko.toreplace.settings.SettingsHolder

class UiSettingsViewModel(
  private val settingsHolder: SettingsHolder
) : ViewModel() {

  fun saveFontSize(size: Float) {
    settingsHolder.storeFontSize(size.toInt())
  }

  fun getFontSize() = settingsHolder.getFontSize()

  fun getMarginSize() = settingsHolder.getMarginSize()

  fun saveMarginSize(size: Float) {
    settingsHolder.storeMarginSize(size.toInt())
  }

  fun toggleOrder(isReversed: Boolean) {
    settingsHolder.storeOrder(isReversed)
  }

  fun isOrderReversed() = settingsHolder.isOrderReversed()
}
