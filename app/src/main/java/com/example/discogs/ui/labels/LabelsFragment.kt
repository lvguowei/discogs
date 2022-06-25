package com.example.discogs.ui.labels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.discogs.R
import com.example.discogs.databinding.FragmentLabelsBinding
import com.example.discogs.ui.common.GridItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelsFragment : Fragment() {

  private lateinit var binding: FragmentLabelsBinding

  private val viewModel: LabelsViewModel by viewModel()

  private lateinit var labelsAdapter: LabelsAdapter

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
    setupViews()
    observeViewModel()
  }

  private fun setupViews() {
    setupRecyclerView()
    setupSearchView()
  }

  private fun setupSearchView() {
    binding.button.setOnClickListener {
      viewModel.searchLabels(binding.search.text.toString())
    }
  }

  private fun setupRecyclerView() {
    labelsAdapter = LabelsAdapter {
      val action = LabelsFragmentDirections.actionLabelsFragmentToReleasesFragment(it.id)
      findNavController().navigate(action)
    }

    binding.labelsRecyclerView.apply {
      adapter = labelsAdapter
      addItemDecoration(GridItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing)))
    }
  }


  private fun observeViewModel() {
    viewModel.labels.observe(viewLifecycleOwner) {
      labelsAdapter.setData(it)
    }

    viewModel.error.observe(viewLifecycleOwner) {
      Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }
  }
}
