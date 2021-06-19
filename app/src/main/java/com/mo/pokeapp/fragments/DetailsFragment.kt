package com.mo.pokeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mo.pokeapp.R
import com.mo.pokeapp.adapters.EvolutionChainAdapter
import com.mo.pokeapp.adapters.FlavorsAdapter
import com.mo.pokeapp.adapters.TitleAdapter
import com.mo.pokeapp.core.BaseFragment
import com.mo.pokeapp.databinding.FragmentDetailsBinding
import com.mo.pokeapp.databinding.LayoutErrorBinding
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

    private val flavorsTitleAdapter by lazy {
        TitleAdapter()
    }

    private val evolutionChainTitleAdapter by lazy {
        TitleAdapter()
    }

    private val concatAdapter by lazy {
        ConcatAdapter().also {
            it.addAdapter(evolutionChainTitleAdapter)
            it.addAdapter(evolutionChainAdapter)
            it.addAdapter(flavorsTitleAdapter)
            it.addAdapter(flavorsAdapter)
        }
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
            flavorsTitleAdapter.submitList(listOf(getString(R.string.flavours)))
            flavorsAdapter.submitList(it.flavorTexts)
        })

        viewModel.evolutionChain.observe(viewLifecycleOwner, {
            evolutionChainTitleAdapter.submitList(listOf(getString(R.string.evolution_chain)))
            evolutionChainAdapter.submitList(it)
            binding.recyclerView.scrollToPosition(0)
        })

        viewModel.errorObservable.observe(viewLifecycleOwner, {
            val msg = it.getContentIfNotHandled()
            msg?.let {
                showError(getString(msg))
            }
        })
    }

    private fun showError(msg: String) {
        val bottomSheet = BottomSheetDialog(requireContext())

        val layoutErrorBinding = LayoutErrorBinding.inflate(layoutInflater)
        bottomSheet.setContentView(layoutErrorBinding.root)

        bottomSheet.dismissWithAnimation = true

        layoutErrorBinding.msgTv.text = msg

        layoutErrorBinding.okButton.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }

    private fun setupRecyclerView() {
        val rv = binding.recyclerView
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = concatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}