package com.example.discogs.usecases

import com.example.discogs.network.DiscogsApi
import com.example.discogs.usecases.models.LabelModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetLabelsUseCase(private val discogsApi: DiscogsApi) {

  fun getLabels(query: String? = null): Single<List<LabelModel>> =
    discogsApi.searchLabels(query.orEmpty())
      .flattenAsObservable { it.results }
      .flatMapSingle { searchLabel ->
        Single.zip(
          discogsApi.getLabel(searchLabel.id),
          discogsApi.getLabelReleases(searchLabel.id)
        ) { label, releases ->
          LabelModel(
            id = label.id,
            name = label.name,
            numberOfReleases = releases.pagination.items,
            thumb = label.images?.firstOrNull()?.uri,
          )
        }
          .subscribeOn(Schedulers.io())
      }
      .toList()
      .subscribeOn(Schedulers.io())
}
