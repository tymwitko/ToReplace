package com.tymwitko.toreplace.newtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tymwitko.toreplace.list.CycleType
import com.tymwitko.toreplace.list.Interval
import com.tymwitko.toreplace.list.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class NewTaskViewModel(
  private val submitTaskUseCase: SubmitTaskUseCase,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
  fun submit(
    title: String,
    desc: String,
    number: Int,
    cycleType: CycleType,
    startDate: LocalDate
  ) {
    viewModelScope.launch(dispatcher) {
      submitTaskUseCase(
        Task(
          1,
          title,
          desc,
          Interval(number, cycleType),
          startDate
        )
      )
    }
  }
}
