package com.tymwitko.toreplace.common.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReplaceDao {
  @Insert
  suspend fun insert(entry: ChoreEntry)

  @Update
  suspend fun update(entry: ChoreEntry)

  @Query("SELECT * FROM chore WHERE id = :id")
  suspend fun getFromWhitelistByPackageId(id: Long): ChoreEntry?

  @Query("SELECT * FROM chore")
  suspend fun getFullWhitelist(): List<ChoreEntry>
}
