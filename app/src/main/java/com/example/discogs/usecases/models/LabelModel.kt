package com.example.discogs.usecases.models

data class LabelModel(
  val id: String,
  val name: String,
  val numberOfReleases: Int,
  val thumb: String?
)
