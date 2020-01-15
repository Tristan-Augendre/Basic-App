package com.evendred.basicapp.ui.component.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericRecyclerViewAdapter<Data, ViewHolder: RecyclerView.ViewHolder>: RecyclerView.Adapter<ViewHolder>() {
    private var dataList: List<Data> = emptyList()

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = onBindViewHolder(holder, dataList[position])

    fun swapData(dataList: List<Data>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): ViewHolder
    abstract fun onBindViewHolder(viewHolder: ViewHolder, data: Data)
}