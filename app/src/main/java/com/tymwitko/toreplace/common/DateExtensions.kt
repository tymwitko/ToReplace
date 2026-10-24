package com.tymwitko.toreplace.common

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun LocalDate.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDate {
  val timeZone = TimeZone.currentSystemDefault()
  return atStartOfDayIn(timeZone)
    .plus(value, unit)
    .toLocalDateTime(timeZone)
    .date
}

fun LocalDate.daysSince(other: LocalDate): Int {
  val timeZone = TimeZone.currentSystemDefault()
  return atStartOfDayIn(timeZone)
    .minus(other.atStartOfDayIn(timeZone))
    .inWholeDays
    .toInt()
}

