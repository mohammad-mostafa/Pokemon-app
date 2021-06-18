package com.mo.pokeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mo.pokeapp.R
import com.mo.pokeapp.adapters.SpeciesListAdapter
import com.mo.pokeapp.adapters.SpeciesViewHolder
import com.mo.pokeapp.core.BaseFragment
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.FragmentSpeciesListBinding
import com.mo.pokeapp.databinding.LayoutErrorBinding
import com.mo.pokeapp.viewmodels.SpeciesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpeciesListFragment : BaseFragment() {

    private var _binding: FragmentSpeciesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeciesListViewModel by viewModels()

    private val errorBottomSheet by lazy {
        BottomSheetDialog(requireContext())
            .also { bottomSheet ->
                val layoutErrorBinding = LayoutErrorBinding.inflate(layoutInflater)
                bottomSheet.setContentView(layoutErrorBinding.root)

                bottomSheet.dismissWithAnimation = true

                layoutErrorBinding.okButton.setOnClickListener {
                    bottomSheet.dismiss()
                }
            }
    }

    private val adapter by lazy {
        SpeciesListAdapter(object : SpeciesViewHolder.SpeciesViewHolderCallback {
            override fun onSpeciesClicked(item: SpeciesVO?) {
                viewModel.onSpeciesItemClicked(item)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeciesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        viewModel.speciesList.observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->

                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                showErrorMsg(loadStates.refresh is LoadState.Error, getString(R.string.error_loading_list))
            }
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner, {

            it.getContentIfNotHandled()?.let { url ->

                val action =
                    SpeciesListFragmentDirections.actionSpeciesListFragmentToDetailsFragment(url)
                findNavController().navigate(action)
            }
        })
    }

    private fun showErrorMsg(show: Boolean, msg: String) {

        if (show) {
            LayoutErrorBinding.inflate(layoutInflater).msgTv.text = msg
            errorBottomSheet.show()
        } else {
            if (errorBottomSheet.isShowing) errorBottomSheet.dismiss()
        }
    }

    private fun setUpRecyclerView() {
        val rv = binding.recyclerView
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}