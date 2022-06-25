package com.example.discogs.di

import com.example.discogs.network.AuthInterceptor
import com.example.discogs.network.DiscogsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

  // OKHttpClient
  single {
    OkHttpClient.Builder()
      .addInterceptor(AuthInterceptor())
      .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
      .build()
  }

  // DiscogsApi
  single {
    Retrofit.Builder()
      .baseUrl("https://api.discogs.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(get())
      .build()
      .create(DiscogsApi::class.java)
  }
}
