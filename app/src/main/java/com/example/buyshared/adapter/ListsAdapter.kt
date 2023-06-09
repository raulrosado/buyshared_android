package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.ui.MainViewModel

class ListsAdapter(
    private val lists: List<ListsEntity>,
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<ListsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListsViewHolder(layoutInflater.inflate(R.layout.item_list,parent,false),parent,requireActivity,mainViewModel)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        val item = lists[position]
        holder.render(item)
    }
}