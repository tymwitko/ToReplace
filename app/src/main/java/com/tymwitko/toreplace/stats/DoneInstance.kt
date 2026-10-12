package com.tymwitko.toreplace.stats

import kotlinx.datetime.LocalDate

data class DoneInstance(
  val id: Long,
  val timeDone: LocalDate,
  val taskId: Long,
  val daysLeft: Long
)
