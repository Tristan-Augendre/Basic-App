package com.evendred.basicapp.ui.champion.list

import android.view.ViewGroup
import com.evendred.basicapp.ui.component.adapter.GenericRecyclerViewAdapter

class ChampionsListAdapter: GenericRecyclerViewAdapter<Champion, ChampionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): ChampionViewHolder = ChampionViewHolder(parent)

    override fun onBindViewHolder(viewHolder: ChampionViewHolder, data: Champion) {
        viewHolder.setChampion(data)
    }
}