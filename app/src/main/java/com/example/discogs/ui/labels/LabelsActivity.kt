package com.example.discogs.ui.labels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.discogs.R
import com.example.discogs.databinding.ActivityLabelsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelsActivity : AppCompatActivity() {

  private val viewModel: LabelsViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DataBindingUtil.setContentView<ActivityLabelsBinding?>(this, R.layout.activity_labels).apply {
      lifecycleOwner = this@LabelsActivity
      vm = viewModel
    }

    observeViewModel()
  }

  private fun observeViewModel() {
    viewModel.labels.observe(this) {
      Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
    }

    viewModel.loading.observe(this) {

    }

    viewModel.error.observe(this) {
      Toast.makeText(this, it, Toast.LENGTH_LONG).show()
    }
  }
}
