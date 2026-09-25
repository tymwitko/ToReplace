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

  constructor(
    name: String,
    description: String,
    intervalCode: Interval,
    lastTimeDone: LocalDate
  ) : this(
    name = name,
    description = description,
    intervalCode = intervalCode.toCode(),
    lastTimeDone = lastTimeDone.toString()
  )

  companion object {
    operator fun invoke(task: Task): ChoreEntry =
      task.id?.let {
        ChoreEntry(
          id = task.id,
          name = task.name,
          description = task.description,
          intervalCode = task.interval.toCode(),
          lastTimeDone = task.lastTimeDone.toString()
        )
      } ?: ChoreEntry(
        name = task.name,
        description = task.description,
        intervalCode = task.interval.toCode(),
        lastTimeDone = task.lastTimeDone.toString()
      )
  }
}

fun ChoreEntry.toDomain() = Task(
  id,
  name,
  description,
  Interval(intervalCode),
  LocalDate.parse(lastTimeDone)
)
