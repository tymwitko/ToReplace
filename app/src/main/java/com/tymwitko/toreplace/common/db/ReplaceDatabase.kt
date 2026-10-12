package com.tymwitko.toreplace.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.stats.db.StatsEntry

@Database(entities = [ChoreEntry::class, StatsEntry::class], version = 1, exportSchema = false)
abstract class ReplaceDatabase : RoomDatabase() {
  abstract fun replaceDao(): ReplaceDao
  abstract fun statsDao(): StatsDao
}
