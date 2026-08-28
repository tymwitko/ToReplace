package com.tymwitko.toreplace.list.db

import com.tymwitko.toreplace.common.db.ReplaceDao

class ChoreRepository(private val replaceDao: ReplaceDao) {
  suspend fun getAllChores() = replaceDao.getFullList()
    .map { it.toDomain() }
}
