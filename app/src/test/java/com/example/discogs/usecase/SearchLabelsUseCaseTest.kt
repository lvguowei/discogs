package com.example.discogs.usecase

import com.example.discogs.common.BaseTest
import com.example.discogs.network.DiscogsApi
import com.example.discogs.network.schemas.*
import com.example.discogs.usecases.SearchLabelsUseCase
import com.example.discogs.usecases.models.LabelModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class SearchLabelsUseCaseTest : BaseTest() {

  private val api = mockk<DiscogsApi>()

  private lateinit var useCase: SearchLabelsUseCase

  @Before
  fun setup() {
    useCase = SearchLabelsUseCase(api)
  }

  @Test
  fun testSearchLabels() {
    val label1Id = "1"
    val label2Id = "2"

    val label1Name = "name1"
    val label2Name = "name2"

    val label1Image = "image1"
    val label2Image = "image2"

    val label1Releases = 10
    val label2Releases = 20

    val label1ReleaseId = "1"
    val label1ReleaseTitle = "label1-release-title"
    val label1ReleaseYear = "1999"
    val label1ReleaseThumb = "url1"

    val label2ReleaseId = "2"
    val label2ReleaseTitle = "label2-release-title"
    val label2ReleaseYear = "2000"
    val label2ReleaseThumb = "url2"


    every { api.searchLabels(any()) }
      .returns(
        Single.just(
          SearchLabelsResponseSchema(
            results = listOf(
              SearchLabelSchema(label1Id),
              SearchLabelSchema(label2Id),
            )
          )
        )
      )

    every { api.getLabel(label1Id) }
      .returns(
        Single.just(
          LabelSchema(
            id = label1Id,
            name = label1Name,
            images = listOf(LabelImageSchema(label1Image))
          )
        )
      )

    every { api.getLabel(label2Id) }
      .returns(
        Single.just(
          LabelSchema(
            id = label2Id,
            name = label2Name,
            images = listOf(LabelImageSchema(label2Image))
          )
        )
      )

    every { api.getLabelReleases(label1Id) }
      .returns(
        Single.just(
          LabelReleasesResponseSchema(
            pagination = PaginationSchema(label1Releases),
            releases = listOf(
              LabelReleaseSchema(
                id = label1ReleaseId,
                title = label1ReleaseTitle,
                year = label1ReleaseYear,
                thumb = label1ReleaseThumb
              )
            )
          )
        )
      )

    every { api.getLabelReleases(label2Id) }
      .returns(
        Single.just(
          LabelReleasesResponseSchema(
            pagination = PaginationSchema(label2Releases),
            releases = listOf(
              LabelReleaseSchema(
                id = label2ReleaseId,
                title = label2ReleaseTitle,
                year = label2ReleaseYear,
                thumb = label2ReleaseThumb
              )
            )
          )
        )
      )

    useCase.execute("").test()
      .assertValue(
        listOf(
          LabelModel(label1Id, label1Name, label1Releases, label1Image),
          LabelModel(label2Id, label2Name, label2Releases, label2Image),
        )
      )
  }
}
