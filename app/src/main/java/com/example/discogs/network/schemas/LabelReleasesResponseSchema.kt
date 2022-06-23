package com.example.discogs.network.schemas

import com.google.gson.annotations.SerializedName

data class LabelReleasesResponseSchema(
  @SerializedName("pagination") val pagination: PaginationSchema,
  @SerializedName("releases") val releases: List<ReleaseSchema>
)

data class PaginationSchema(
  @SerializedName("items") val items: Int
)

data class ReleaseSchema(
  @SerializedName("title") val title: String,
  @SerializedName("year") val year: String,
  @SerializedName("thumb") val thumb: String?
)
