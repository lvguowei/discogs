package com.example.discogs.network

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val url = chain.request().url.newBuilder()
      .addQueryParameter("key", "ExtbXdYBmYGDERwCJpFs")
      .addQueryParameter("secret", "KLDuoiawwFrFNpRVqYZMSzLOuwZdABIW")
      .build()

    val requestBuilder = chain.request().newBuilder().url(url)
    return chain.proceed(requestBuilder.build())
  }
}
