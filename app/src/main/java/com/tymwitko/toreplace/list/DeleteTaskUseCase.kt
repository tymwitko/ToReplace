package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository

class DeleteTaskUseCase(private val choreRepository: ChoreRepository) {
  suspend operator fun invoke(task: Task) {
    choreRepository.deleteChore(ChoreEntry(task))
  }
}
