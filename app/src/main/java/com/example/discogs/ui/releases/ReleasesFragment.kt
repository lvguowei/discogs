package com.example.discogs.ui.releases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.discogs.R
import com.example.discogs.databinding.FragmentReleasesBinding
import com.example.discogs.ui.labels.LabelsViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReleasesFragment : Fragment() {

  private lateinit var binding: FragmentReleasesBinding

  private val args: ReleasesFragmentArgs by navArgs()

  private val viewModel: ReleasesViewModel by viewModel() {
    parametersOf(args.labelId)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_releases, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.apply {
      vm = viewModel
      lifecycleOwner = viewLifecycleOwner
    }
  }
}
