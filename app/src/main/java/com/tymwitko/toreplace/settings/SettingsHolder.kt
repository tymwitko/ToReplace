package com.tymwitko.toreplace.settings

import android.content.SharedPreferences
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.tymwitko.toreplace.common.Consts.DEFAULT_FONT_SIZE
import com.tymwitko.toreplace.common.Consts.DEFAULT_MARGIN_SIZE
import com.tymwitko.toreplace.common.Consts.FONT_SIZE_ALIAS
import com.tymwitko.toreplace.common.Consts.IS_REVERSED_ORDER_ALIAS
import com.tymwitko.toreplace.common.Consts.MARGIN_SIZE_ALIAS
import com.tymwitko.toreplace.list.SortingType

class SettingsHolder(
  private val sharedPrefs: SharedPreferences
) {
  fun storeFontSize(newSize: Int) {
    saveInt(FONT_SIZE_ALIAS, newSize)
  }

  fun getFontSize() = sharedPrefs.getInt(FONT_SIZE_ALIAS, DEFAULT_FONT_SIZE).sp

  fun storeMarginSize(size: Int) {
    saveInt(MARGIN_SIZE_ALIAS, size)
  }

  fun getMarginSize() = sharedPrefs.getInt(MARGIN_SIZE_ALIAS, DEFAULT_MARGIN_SIZE).dp

  fun storeOrder(sortingType: SortingType) {
    saveString(IS_REVERSED_ORDER_ALIAS, sortingType.name)
  }

  fun getOrder() =
    sharedPrefs.getString(IS_REVERSED_ORDER_ALIAS, SortingType.NEWEST.name)
      ?.let(SortingType::valueOf) ?: SortingType.NEWEST

  private fun saveString(key: String, value: String) {
    sharedPrefs.edit(commit = true) {
      putString(key, value)
    }
  }

  private fun saveInt(key: String, value: Int) {
    sharedPrefs.edit(commit = true) {
      putInt(key, value)
    }
  }
}
