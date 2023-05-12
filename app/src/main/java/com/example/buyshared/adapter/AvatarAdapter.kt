package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.ui.MainViewModel

class AvatarAdapter(
    private val lists: List<Avatar>,
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<AvatarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AvatarsViewHolder(layoutInflater.inflate(R.layout.item_avatar,parent,false),parent,requireActivity)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: AvatarsViewHolder, position: Int) {
        val item = lists[position]
        holder.render(item)
    }
}