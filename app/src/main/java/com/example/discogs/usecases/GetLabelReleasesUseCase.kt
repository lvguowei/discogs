package com.example.discogs.usecases

import com.example.discogs.network.DiscogsApi
import com.example.discogs.usecases.models.ReleaseModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetLabelReleasesUseCase(private val api: DiscogsApi) {

  fun get(labelId: String): Single<List<ReleaseModel>> {
    return api.getLabelReleases(labelId)
      .flattenAsObservable {
        it.releases
      }
      .flatMapSingle { labelRelease ->
        api.getRelease(labelRelease.id)
          .map { release ->
            ReleaseModel(
              id = release.id,
              title = labelRelease.title,
              year = labelRelease.year,
              thumb = labelRelease.thumb,
              want = release.community.want,
              have = release.community.have
            )
          }
          .subscribeOn(Schedulers.io())
      }
      .toList()
      .subscribeOn(Schedulers.io())
  }
}
