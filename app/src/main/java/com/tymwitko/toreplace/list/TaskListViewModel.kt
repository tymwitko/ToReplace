package com.tymwitko.toreplace.list

import androidx.lifecycle.ViewModel
import java.time.LocalDate

class TaskListViewModel : ViewModel() {
  fun getStartDate(taskId: Int): LocalDate {
    return LocalDate.now()
  }
}
