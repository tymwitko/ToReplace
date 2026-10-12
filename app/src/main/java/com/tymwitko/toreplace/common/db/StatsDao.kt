package com.tymwitko.toreplace.common.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tymwitko.toreplace.stats.db.StatsEntry

@Dao
interface StatsDao {
  @Insert
  suspend fun insert(entry: StatsEntry)

  @Update
  suspend fun update(entry: StatsEntry)

  @Delete
  suspend fun delete(entry: StatsEntry)

  @Query("SELECT * FROM stats WHERE id = :id")
  suspend fun getChoreById(id: Long): StatsEntry?

  @Query("SELECT * FROM stats")
  suspend fun getFullList(): List<StatsEntry>

  @Query("DELETE FROM stats WHERE taskId = :taskId")
  suspend fun clearTaskStats(taskId: Long)
}
