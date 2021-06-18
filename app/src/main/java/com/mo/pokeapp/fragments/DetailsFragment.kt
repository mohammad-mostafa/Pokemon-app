package com.mo.pokeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mo.pokeapp.core.BaseFragment
import com.mo.pokeapp.databinding.FragmentDetailsBinding
import com.mo.pokeapp.viewmodels.SpeciesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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

        val args: DetailsFragmentArgs by navArgs()
        viewModel.setData(args.detailsUrl)

        viewModel.speciesDetails.observe(viewLifecycleOwner, {
            binding.nameTv.text = it.name
        })

        viewModel.evolutionChain.observe(viewLifecycleOwner, {
            Log.d("TAG", it.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}