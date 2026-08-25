package com.tymwitko.toreplace.list

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
