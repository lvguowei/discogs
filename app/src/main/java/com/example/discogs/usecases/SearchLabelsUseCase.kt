package com.example.discogs.usecases

import com.example.discogs.network.DiscogsApi
import com.example.discogs.usecases.models.LabelModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchLabelsUseCase(private val api: DiscogsApi) {

  fun execute(query: String): Single<List<LabelModel>> =
    api.searchLabels(query)
      .flattenAsObservable { it.results }
      .flatMapSingle { searchLabel ->
        Single.zip(
          api.getLabel(searchLabel.id),
          api.getLabelReleases(searchLabel.id)
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
