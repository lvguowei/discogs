package com.example.discogs.ui.labels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.discogs.usecases.SearchLabelsUseCase
import com.example.discogs.usecases.models.LabelModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LabelsViewModel(private val getLabelsUseCase: SearchLabelsUseCase) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  private val _loading = MutableLiveData(false)
  val loading: LiveData<Boolean> = _loading

  private val _labels = MutableLiveData<List<LabelModel>>()
  val labels: LiveData<List<LabelModel>> = _labels

  private val _error = MutableLiveData<String>()
  val error: LiveData<String> = _error

  init {
    searchLabels()
  }

  fun searchLabels(query: String? = null) {
    compositeDisposable.clear()
    getLabelsUseCase.search(query.orEmpty())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { _loading.value = true }
      .doFinally { _loading.value = false }
      .subscribe(
        { labels ->
          _labels.value = labels
        }, { e ->
          _error.value = e.message
        })
      .also {
        compositeDisposable.add(it)
      }
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
