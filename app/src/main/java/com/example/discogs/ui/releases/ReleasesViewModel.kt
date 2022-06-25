package com.example.discogs.ui.releases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.discogs.usecases.GetLabelReleasesUseCase
import com.example.discogs.usecases.models.ReleaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ReleasesViewModel(
  private val labelId: String,
  private val getLabelReleasesUseCase: GetLabelReleasesUseCase
) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  private val _loading = MutableLiveData(false)
  val loading: LiveData<Boolean> = _loading

  private val _releases = MutableLiveData<List<ReleaseModel>>()
  val releases: LiveData<List<ReleaseModel>> = _releases

  private val _error = MutableLiveData<String>()
  val error: LiveData<String> = _error

  init {
    getLabelReleasesUseCase.get(labelId)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { _loading.value = true }
      .doFinally { _loading.value = false }
      .subscribe(
        { releases ->
          _releases.value = releases
        },
        { e ->
          _error.value = e.message
        })
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
