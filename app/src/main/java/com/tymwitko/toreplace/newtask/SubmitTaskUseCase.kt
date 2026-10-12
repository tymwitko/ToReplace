package com.tymwitko.toreplace.newtask

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.Task
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository

class SubmitTaskUseCase(private val choreRepository: ChoreRepository) {
  suspend operator fun invoke(
    task: Task
  ): Result<Task, TaskListError> = try {
    val entry = ChoreEntry(
      name = task.name,
      description = task.description,
      intervalCode = task.interval.toCode(),
      lastTimeDone = task.lastTimeDone.toString()
    )
    choreRepository.addChore(entry)
    Result.Success(task)
  } catch (e: Exception) {
    Result.Failure(TaskListError.Exception(e.stackTraceToString()))
  }
}
