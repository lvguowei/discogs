package com.example.discogs.viewmodel

import androidx.lifecycle.Observer
import com.example.discogs.common.BaseTest
import com.example.discogs.common.getOrAwaitValue
import com.example.discogs.ui.labels.LabelsViewModel
import com.example.discogs.usecases.SearchLabelsUseCase
import com.example.discogs.usecases.models.LabelModel
import com.google.common.truth.Truth
import io.mockk.*
import io.reactivex.Single
import org.junit.Test

class LabelsViewModelTest : BaseTest() {

  private val useCase = mockk<SearchLabelsUseCase>()

  private lateinit var viewModel: LabelsViewModel

  @Test
  fun searchLabelsSuccess() {
    val labels = listOf(
      LabelModel(
        id = "1",
        name = "label1",
        numberOfReleases = 1,
        thumb = null
      )
    )

    every { useCase.execute(any()) } returns Single.just(labels)

    viewModel = LabelsViewModel(useCase)

    val loadingObserver = mockk<Observer<Boolean>> {
      every { onChanged(any()) } just Runs
    }
    viewModel.loading.observeForever(loadingObserver)

    viewModel.searchLabels()

    Truth.assertThat(viewModel.labels.getOrAwaitValue()).isEqualTo(labels)

    verifySequence {
      loadingObserver.onChanged(false)
      loadingObserver.onChanged(true)
      loadingObserver.onChanged(false)
    }
  }
}
