package com.tymwitko.toreplace.list

data class Task(
  val name: String,
  val cycleLength: Int,
  val cycleTask: CycleType,
  val dueInDays: Int
)
