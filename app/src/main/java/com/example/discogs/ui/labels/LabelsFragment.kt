package com.example.discogs.ui.labels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.discogs.R
import com.example.discogs.databinding.FragmentLabelsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelsFragment : Fragment() {

  private lateinit var binding: FragmentLabelsBinding

  private val viewModel: LabelsViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_labels, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.apply {
      vm = viewModel
      lifecycleOwner = viewLifecycleOwner
    }

    observeViewModel()
  }


  private fun observeViewModel() {
    viewModel.labels.observe(viewLifecycleOwner) {
      Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
    }

    viewModel.loading.observe(viewLifecycleOwner) {

    }

    viewModel.error.observe(viewLifecycleOwner) {
      Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }
  }
}
