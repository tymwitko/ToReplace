package com.tymwitko.toreplace.stats.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tymwitko.toreplace.stats.DoneInstance
import kotlinx.datetime.LocalDate

@Entity(tableName = "stats")
data class StatsEntry(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,

  @ColumnInfo(name = "whenDone")
  val whenDone: String,

  @ColumnInfo(name = "taskId")
  val taskId: Long,

  @ColumnInfo(name = "daysLeft")
  val daysLeft: Long
)

fun StatsEntry.toDomain() = DoneInstance(
  id,
  LocalDate.parse(whenDone),
  taskId,
  daysLeft
)
