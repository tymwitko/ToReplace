package com.tymwitko.toreplace.common

sealed class TaskListError: Error {
  object Empty: TaskListError()
  data class Exception(val message: String): TaskListError()
}
