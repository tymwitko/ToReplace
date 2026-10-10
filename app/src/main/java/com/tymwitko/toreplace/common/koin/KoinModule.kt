package com.tymwitko.toreplace.common.koin

import android.content.Context
import androidx.room.Room
import com.tymwitko.toreplace.common.Consts.SHARED_PREFS_KEY
import com.tymwitko.toreplace.common.db.ReplaceDao
import com.tymwitko.toreplace.common.db.ReplaceDatabase
import com.tymwitko.toreplace.list.DeleteTaskUseCase
import com.tymwitko.toreplace.list.FetchTasksUseCase
import com.tymwitko.toreplace.list.TaskListViewModel
import com.tymwitko.toreplace.list.UpdateTaskUseCase
import com.tymwitko.toreplace.list.db.ChoreRepository
import com.tymwitko.toreplace.newtask.NewTaskViewModel
import com.tymwitko.toreplace.newtask.SubmitTaskUseCase
import com.tymwitko.toreplace.settings.SettingsHolder
import com.tymwitko.toreplace.settings.SettingsViewModel
import com.tymwitko.toreplace.settings.ui.UiSettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
  viewModel {
    TaskListViewModel(get(), get(), get(), get())
  }
  single {
    Room.databaseBuilder(
      context = androidContext(),
      klass = ReplaceDatabase::class.java,
      name = "replace.db"
    )
      .build()
  }
  single<ReplaceDao> {
    val db = get<ReplaceDatabase>()
    db.replaceDao()
  }
  singleOf(::ChoreRepository)
  singleOf(::FetchTasksUseCase)
  singleOf(::SubmitTaskUseCase)
  singleOf(::UpdateTaskUseCase)
  singleOf(::DeleteTaskUseCase)
  viewModel {
    NewTaskViewModel(get())
  }
  viewModelOf(::UiSettingsViewModel)
  single {
    SettingsHolder(
      androidContext().getSharedPreferences(
        SHARED_PREFS_KEY,
        Context.MODE_PRIVATE
      )
    )
  }
  singleOf(::SettingsViewModel)
}
