package com.example.discogs.ui.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibleOrGone")
fun visibleOrGone(view: View, value: Boolean) {
  view.visibility = if (value) View.VISIBLE else View.GONE
}
