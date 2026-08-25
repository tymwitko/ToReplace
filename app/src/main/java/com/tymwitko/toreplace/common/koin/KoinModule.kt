package com.tymwitko.toreplace.common.koin

import com.tymwitko.toreplace.common.db.ReplaceDao
import com.tymwitko.toreplace.common.db.ReplaceDatabase
import com.tymwitko.toreplace.list.TaskListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
  viewModelOf(::TaskListViewModel)
  single<ReplaceDao> {
    val db = get<ReplaceDatabase>()
    db.replaceDao()
  }
}
