package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.db.ChoreRepository
import com.tymwitko.toreplace.list.db.toDomain

class FetchTasksUseCase(
  private val choreRepository: ChoreRepository
) {
  suspend operator fun invoke(): Result<List<Task>, TaskListError> {
    val res = choreRepository.getAllChores()
    return if (res.isNotEmpty())
      Result.Success(res)
    else Result.Failure(TaskListError.Empty)
  }

  suspend fun fetchSingleTask(taskId: Long): Result<Task, TaskListError> {
    val res = choreRepository.fetchSingleTask(taskId)
    return res?.toDomain()?.let {
      Result.Success(it)
    } ?: Result.Failure(TaskListError.Empty)
  }
}

