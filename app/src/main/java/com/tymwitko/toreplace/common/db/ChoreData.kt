package com.tymwitko.toreplace.common.db

import com.tymwitko.toreplace.list.Interval

data class ChoreData(
  val name: String,
  val description: String,
  val interval: Interval
)
