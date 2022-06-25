package com.example.discogs.network

import com.example.discogs.network.schemas.LabelReleasesResponseSchema
import com.example.discogs.network.schemas.LabelSchema
import com.example.discogs.network.schemas.ReleaseSchema
import com.example.discogs.network.schemas.SearchLabelsResponseSchema
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscogsApi {

  @GET("database/search?type=label&page=1&per_page=5")
  fun searchLabels(@Query("title") title: String): Single<SearchLabelsResponseSchema>

  @GET("labels/{id}")
  fun getLabel(@Path("id") labelId: String): Single<LabelSchema>

  @GET("labels/{id}/releases?page=1&per_page=5")
  fun getLabelReleases(@Path("id") labelId: String): Single<LabelReleasesResponseSchema>

  @GET("releases/{id}")
  fun getRelease(@Path("id") releaseId: String): Single<ReleaseSchema>
}
