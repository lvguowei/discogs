package com.example.discogs.network.schemas

import com.google.gson.annotations.SerializedName

data class SearchLabelsResponseSchema(
  @SerializedName("results") val results: List<SearchLabelSchema>
)

data class SearchLabelSchema(
  @SerializedName("id") val id: String
)
