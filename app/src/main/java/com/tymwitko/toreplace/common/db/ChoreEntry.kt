package com.tymwitko.toreplace.common.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tymwitko.toreplace.list.Interval

@Entity(tableName = "chore")
data class ChoreEntry(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,

  @ColumnInfo(name = "name")
  val name: String,

  @ColumnInfo(name = "desc")
  val description: String,

  @ColumnInfo(name = "interval")
  var intervalCode: String
) {
  constructor(ps: ChoreData) : this(
    name = ps.name,
    description = ps.description,
    intervalCode = ps.interval.toCode()
  )
}

fun ChoreEntry.toDomain() = ChoreData(name, description, Interval(intervalCode))
