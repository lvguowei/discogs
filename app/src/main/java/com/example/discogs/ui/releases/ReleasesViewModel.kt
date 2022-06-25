package com.example.discogs.ui.releases

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class ReleasesViewModel(private val labelId: String) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  init {
    Log.d("test", labelId)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }

  fun getReleases(labelId: String) {
  }
}
