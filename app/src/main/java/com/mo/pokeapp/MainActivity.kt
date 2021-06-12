package com.mo.pokeapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mo.pokeapp.adapters.SpeciesListAdapter
import com.mo.pokeapp.core.BaseActivity
import com.mo.pokeapp.viewmodels.SpeciesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: SpeciesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SpeciesListAdapter()

        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


        lifecycleScope.launch {
            viewModel.fetchSpeciesList().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}