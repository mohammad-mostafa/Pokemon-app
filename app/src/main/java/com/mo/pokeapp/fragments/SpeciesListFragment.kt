package com.mo.pokeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mo.pokeapp.adapters.SpeciesListAdapter
import com.mo.pokeapp.adapters.SpeciesViewHolder
import com.mo.pokeapp.core.BaseFragment
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.databinding.FragmentSpeciesListBinding
import com.mo.pokeapp.viewmodels.SpeciesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpeciesListFragment : BaseFragment() {

    private var _binding: FragmentSpeciesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeciesListViewModel by viewModels()

    private val adapter by lazy {
        SpeciesListAdapter(object : SpeciesViewHolder.SpeciesViewHolderCallback {
            override fun onSpeciesClicked(item: SpeciesListVO?) {
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

        viewModel.navigateToDetails.observe(viewLifecycleOwner, {

            it.getContentIfNotHandled()?.let { url ->

                val action =
                    SpeciesListFragmentDirections.actionSpeciesListFragmentToDetailsFragment(url)
                findNavController().navigate(action)
            }
        })
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