package com.tymwitko.toreplace.common.koin

import com.tymwitko.toreplace.list.TaskListViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

val appModule = module {
  viewModelOf(::TaskListViewModel)
}
