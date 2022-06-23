package com.example.discogs

import android.app.Application
import com.example.discogs.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiscogsApp : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@DiscogsApp)
      modules(appModule)
    }
  }
}
