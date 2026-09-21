package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository
import kotlinx.datetime.LocalDate

class UpdateTaskUseCase(
  private val choreRepository: ChoreRepository
) {
  suspend operator fun invoke(task: Task, newTime: LocalDate): Result<Boolean, TaskListError> =
    try {
      choreRepository.updateChore(
        ChoreEntry(
          task.copy(lastTimeDone = newTime)
        )
      )
      Result.Success(true)
    } catch (e: Exception) {
      Result.Failure(TaskListError.Exception(e.message.orEmpty()))
    }
}
