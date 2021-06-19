package com.mo.pokeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.mo.pokeapp.adapters.EvolutionChainAdapter
import com.mo.pokeapp.adapters.FlavorsAdapter
import com.mo.pokeapp.core.BaseFragment
import com.mo.pokeapp.databinding.FragmentDetailsBinding
import com.mo.pokeapp.viewmodels.SpeciesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val evolutionChainAdapter by lazy {
        EvolutionChainAdapter()
    }

    private val flavorsAdapter by lazy {
        FlavorsAdapter()
    }

    private val viewModel: SpeciesDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val args: DetailsFragmentArgs by navArgs()
        viewModel.setData(args.detailsUrl)

        viewModel.speciesDetails.observe(viewLifecycleOwner, {
            binding.nameTv.text = it.name
            flavorsAdapter.submitList(it.flavorTexts)
        })

        viewModel.evolutionChain.observe(viewLifecycleOwner, {
            evolutionChainAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        val rv = binding.recyclerView
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = ConcatAdapter(flavorsAdapter, evolutionChainAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}