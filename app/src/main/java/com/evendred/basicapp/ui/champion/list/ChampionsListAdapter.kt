package com.evendred.basicapp.ui.champion.list

import android.view.ViewGroup
import com.evendred.basicapp.ui.component.adapter.GenericRecyclerViewAdapter

class ChampionsListAdapter: GenericRecyclerViewAdapter<Champion, ChampionViewHolder>() {
    interface Listener {
        fun onChampionClick(champion: Champion)
    }

    private var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup): ChampionViewHolder = ChampionViewHolder(parent)

    override fun onBindViewHolder(viewHolder: ChampionViewHolder, data: Champion) {
        viewHolder.setChampion(data)
        viewHolder.setOnClickListener { champion -> listener?.onChampionClick(champion) }
    }

    fun setOnChampionCLickListener(listener : (Champion) -> Unit) {
        this.listener = object : Listener {
            override fun onChampionClick(champion: Champion) {
                listener(champion)
            }
        }
    }
}