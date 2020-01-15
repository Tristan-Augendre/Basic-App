package com.evendred.basicapp.ui.champion.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.evendred.basicapp.R
import com.evendred.basicapp.extensions.cast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_champions_list.*
import javax.inject.Inject

class ChampionsListFragment: Fragment() {
    @Inject
    lateinit var viewModel: ChampionsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ChampionsListDaggerInjection.injectChampionsListFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_champions_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championsListView.layoutManager = LinearLayoutManager(context)
        championsListView.adapter = ChampionsListAdapter().apply {
            setOnChampionCLickListener { champion -> viewModel.onChampionCLick(champion) }
        }

        viewModel.observeChampionsList(this) { championsListView.adapter?.cast<ChampionsListAdapter>()?.swapData(it) }
        viewModel.observeErrorSnackbar(this) { Snackbar.make(view, it, Snackbar.LENGTH_LONG).show() }
        viewModel.observeNavigate(this) { findNavController().navigate(it) }

        viewModel.onViewCreated()
    }
}