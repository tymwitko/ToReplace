package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.list.db.ChoreRepository

class FetchTasksUseCase(
  private val choreRepository: ChoreRepository
) {
  suspend operator fun invoke(): Result<List<Task>, TaskListError> {
    val res = choreRepository.getAllChores()
    return if (res.isNotEmpty())
      Result.Success(res)
    else Result.Failure(TaskListError.Empty)
  }
}

