package com.tymwitko.toreplace.common.ui

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun Painter.toImageBitmap(
  density: Density,
  layoutDirection: LayoutDirection,
): ImageBitmap {
  val bitmap = ImageBitmap(intrinsicSize.width.toInt(), intrinsicSize.height.toInt())
  val canvas = Canvas(bitmap)
  CanvasDrawScope().draw(density, layoutDirection, canvas, intrinsicSize) {
    draw(intrinsicSize)
  }
  return bitmap
}
