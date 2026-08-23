package com.tymwitko.toreplace.common.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules

class ToReplaceKoinApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    GlobalContext.startKoin {
      androidContext(this@ToReplaceKoinApplication)
      appModule
    }
    loadKoinModules(appModule)
  }
}
