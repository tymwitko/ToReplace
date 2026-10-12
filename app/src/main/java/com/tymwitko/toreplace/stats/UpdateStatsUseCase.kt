package com.tymwitko.toreplace.stats

import com.tymwitko.toreplace.common.Result
import com.tymwitko.toreplace.common.TaskListError
import com.tymwitko.toreplace.stats.db.StatsEntry
import com.tymwitko.toreplace.stats.db.StatsRepository

class UpdateStatsUseCase(private val statsRepository: StatsRepository) {
  suspend operator fun invoke(done: DoneInstance): Result<Boolean, TaskListError> =
    try {
      statsRepository.addInstance(StatsEntry(done))
      Result.Success(true)
    } catch (e: Exception) {
      Result.Failure(TaskListError.Exception(e.message.orEmpty()))
    }

  suspend fun removeTaskStats(taskId: Long): Result<Boolean, TaskListError> =
    try {
      statsRepository.clearByTask(taskId)
      Result.Success(true)
    } catch (e: Exception) {
      Result.Failure(TaskListError.Exception(e.message.orEmpty()))
    }
}
