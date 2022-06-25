package com.example.discogs.di

import com.example.discogs.usecases.GetLabelReleasesUseCase
import com.example.discogs.usecases.SearchLabelsUseCase
import org.koin.dsl.module

val useCaseModule = module {
  factory {
    SearchLabelsUseCase(get())
  }
  factory {
    GetLabelReleasesUseCase(get())
  }
}
