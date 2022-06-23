package com.example.discogs.network.schemas

import com.google.gson.annotations.SerializedName

data class LabelSchema(
  @SerializedName("id") val id: String,
  @SerializedName("name") val name: String,
  @SerializedName("images") val images: List<LabelImageSchema>?
)

data class LabelImageSchema(
  @SerializedName("uri") val uri: String
)
