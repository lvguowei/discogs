package com.example.discogs.network.schemas

import com.google.gson.annotations.SerializedName

data class LabelReleasesResponseSchema(
  @SerializedName("pagination") val pagination: PaginationSchema,
  @SerializedName("releases") val releases: List<LabelReleaseSchema>
)

data class PaginationSchema(
  @SerializedName("items") val items: Int
)

data class LabelReleaseSchema(
  @SerializedName("id") val id: String,
  @SerializedName("title") val title: String,
  @SerializedName("year") val year: String,
  @SerializedName("thumb") val thumb: String?
)
