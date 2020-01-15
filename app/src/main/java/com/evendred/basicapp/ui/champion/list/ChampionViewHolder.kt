package com.evendred.basicapp.ui.champion.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.evendred.basicapp.R
import com.evendred.basicapp.extensions.cast
import com.evendred.basicapp.extensions.setImageUrl

class ChampionViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_champion, parent, false)) {
    private val icon: AppCompatImageView = itemView.findViewById(R.id.icon)
    private val name: AppCompatTextView = itemView.findViewById(R.id.name)
    private val title: AppCompatTextView = itemView.findViewById(R.id.title)

    fun setChampion(champion: Champion) {
        itemView.tag = champion

        icon.setImageUrl(champion.image)
        name.text = champion.name
        title.text = champion.title
    }

    fun setOnClickListener(listener: (Champion) -> Unit) {
        itemView.setOnClickListener { listener(itemView.tag.cast()) }
    }
}