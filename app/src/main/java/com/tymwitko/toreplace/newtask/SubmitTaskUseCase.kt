package com.tymwitko.toreplace.newtask

import com.tymwitko.toreplace.list.Task
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository

class SubmitTaskUseCase(private val choreRepository: ChoreRepository) {
  suspend operator fun invoke(
    task: Task
  ) {
    val entry = ChoreEntry(
      task.name,
      task.description,
      task.interval,
      task.lastTimeDone
    )
    choreRepository.addChore(entry)
  }
}
