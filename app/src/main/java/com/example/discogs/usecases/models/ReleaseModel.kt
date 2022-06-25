package com.example.discogs.usecases.models

data class ReleaseModel(
  val id: String,
  val title: String,
  val year: String,
  val want: Int,
  val have: Int,
  val thumb: String?
)
