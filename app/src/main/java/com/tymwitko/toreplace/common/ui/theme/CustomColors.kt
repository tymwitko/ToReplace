package com.tymwitko.toreplace.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
  val warning: Color
)

val LightColors = CustomColors(
  warning = OrangeOnLight
)

val DarkColors = CustomColors(
  warning = OrangeOnDark
)

val LocalAppColors = staticCompositionLocalOf {
  LightColors
}
