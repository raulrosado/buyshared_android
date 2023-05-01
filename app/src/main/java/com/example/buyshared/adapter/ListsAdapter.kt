package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity

class ListsAdapter(private val lists: List<ListsEntity> ) : RecyclerView.Adapter<ListsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListsViewHolder(layoutInflater.inflate(R.layout.item_list,parent,false),parent)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        val item = lists[position]
        holder.render(item)
    }
}