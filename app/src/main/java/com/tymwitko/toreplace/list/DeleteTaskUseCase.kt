package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository

class DeleteTaskUseCase(private val choreRepository: ChoreRepository) {
  suspend operator fun invoke(task: Task): Result<Task, TaskListError> =
    try {
      choreRepository.deleteChore(ChoreEntry(task))
      Result.Success(task)
    } catch (e: Exception) {
      Result.Failure(TaskListError.Exception(e.message.orEmpty()))
    }
}
