package com.tymwitko.toreplace.list

data class TaskViewData(
  val task: Task,
  val dueInDays: Int
)

fun List<TaskViewData>.sortedBy(sortingType: SortingType) = when (sortingType) {
  SortingType.NEWEST -> sortedBy { it.task.id }
  SortingType.OLDEST -> sortedByDescending { it.task.id }
  SortingType.MOST_URGENT -> sortedBy { it.dueInDays }
  SortingType.LEAST_URGENT -> sortedByDescending { it.dueInDays }
}
