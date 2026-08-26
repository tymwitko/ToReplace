package com.tymwitko.toreplace.list

import java.time.LocalDate

data class Task(
  val id: Long,
  val name: String,
  val description: String,
  val interval: Interval,
  val startDate: LocalDate
)
