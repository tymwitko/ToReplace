package com.tymwitko.toreplace.stats.db

import com.tymwitko.toreplace.common.db.StatsDao

class StatsRepository(private val statsDao: StatsDao) {
  suspend fun getAllInstances() = statsDao.getFullList()
    .map { it.toDomain() }

  suspend fun addInstance(instance: StatsEntry) {
    statsDao.insert(instance)
  }

  suspend fun updateInstance(instance: StatsEntry) {
    statsDao.update(instance)
  }

  suspend fun deleteInstance(instance: StatsEntry) {
    statsDao.delete(instance)
  }

  suspend fun clearByTask(taskId: Long) {
    statsDao.clearTaskStats(taskId)
  }
}
