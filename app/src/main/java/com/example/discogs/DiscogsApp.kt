package com.example.discogs

import android.app.Application
import android.util.Log
import com.example.discogs.di.appModule
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiscogsApp : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@DiscogsApp)
      modules(appModule)
    }
    setupRxJava()
  }

  private fun setupRxJava() {
    RxJavaPlugins.setErrorHandler {
      Log.e("RxJava", it.message.orEmpty())
    }
  }
}
