package com.tymwitko.toreplace.list.db

import com.tymwitko.toreplace.common.db.ReplaceDao

class ChoreRepository(private val replaceDao: ReplaceDao) {
  suspend fun getAllChores() = replaceDao.getFullList()
    .map { it.toDomain() }

  suspend fun addChore(chore: ChoreEntry) {
    replaceDao.insert(chore)
  }

  suspend fun updateChore(chore: ChoreEntry) {
    replaceDao.update(chore)
  }

  suspend fun deleteChore(chore: ChoreEntry) {
    replaceDao.delete(chore)
  }
}
