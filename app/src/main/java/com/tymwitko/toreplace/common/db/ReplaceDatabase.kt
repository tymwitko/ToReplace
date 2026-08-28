package com.tymwitko.toreplace.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tymwitko.toreplace.list.db.ChoreEntry

@Database(entities = [ChoreEntry::class], version = 1, exportSchema = false)
abstract class ReplaceDatabase : RoomDatabase() {
  abstract fun replaceDao(): ReplaceDao
}
