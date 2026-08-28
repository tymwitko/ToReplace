package com.tymwitko.toreplace.common.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tymwitko.toreplace.list.db.ChoreEntry

@Dao
interface ReplaceDao {
  @Insert
  suspend fun insert(entry: ChoreEntry)

  @Update
  suspend fun update(entry: ChoreEntry)

  @Query("SELECT * FROM chore WHERE id = :id")
  suspend fun getChoreById(id: Long): ChoreEntry?

  @Query("SELECT * FROM chore")
  suspend fun getFullList(): List<ChoreEntry>
}
