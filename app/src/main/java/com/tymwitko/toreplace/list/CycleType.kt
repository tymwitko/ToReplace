package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.R

enum class CycleType {
  DAYS, WEEKS, MONTHS, YEARS
}

fun CycleType.toCode() = this.name.first()

fun Char.toCycleType() = when (this) {
  'D' -> CycleType.DAYS
  'W' -> CycleType.WEEKS
  'M' -> CycleType.MONTHS
  'Y' -> CycleType.YEARS
  else -> null
}

fun CycleType.toStringResource() = when (this) {
  CycleType.DAYS -> R.plurals.day
  CycleType.WEEKS -> R.plurals.week
  CycleType.MONTHS -> R.plurals.month
  CycleType.YEARS -> R.plurals.year
}
