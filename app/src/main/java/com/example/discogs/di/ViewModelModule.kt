package com.example.discogs.di

import com.example.discogs.ui.labels.LabelsViewModel
import com.example.discogs.ui.releases.ReleasesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel {
    LabelsViewModel(get())
  }
  viewModel {
    //ReleasesViewModel()
      (labelId: String) ->
    ReleasesViewModel(labelId)
  }
}
