package com.tymwitko.toreplace.list.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tymwitko.toreplace.list.Interval
import com.tymwitko.toreplace.list.Task
import kotlinx.datetime.LocalDate

@Entity(tableName = "chore")
data class ChoreEntry(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,

  @ColumnInfo(name = "name")
  val name: String,

  @ColumnInfo(name = "desc")
  val description: String,

  @ColumnInfo(name = "interval")
  var intervalCode: String,

  @ColumnInfo(name = "last_done")
  var lastTimeDone: String
) {
  constructor(task: Task) : this(
    name = task.name,
    description = task.description,
    intervalCode = task.interval.toCode(),
    lastTimeDone = task.lastTimeDone.toString()
  )
}

fun ChoreEntry.toDomain() = Task(
  id,
  name,
  description,
  Interval(intervalCode),
  LocalDate.parse(lastTimeDone)
)
