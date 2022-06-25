package com.example.discogs.network.schemas

import com.google.gson.annotations.SerializedName

data class ReleaseSchema(
  @SerializedName("id") val id: String,
  @SerializedName("community") val community: CommunitySchema
)

data class CommunitySchema(
  @SerializedName("have") val have: Int,
  @SerializedName("want") val want: Int
)
