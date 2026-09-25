package com.tymwitko.toreplace.list

import kotlinx.datetime.LocalDate

data class Task(
  val id: Long?,
  val name: String,
  val description: String,
  val interval: Interval,
  val lastTimeDone: LocalDate
)
