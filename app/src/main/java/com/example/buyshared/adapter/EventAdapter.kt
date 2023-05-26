package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.ui.MainViewModel

class EventAdapter(private val eventosList: List<EventsEntity> ,private val requireActivity: FragmentActivity,private val mainViewModel: MainViewModel) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(layoutInflater.inflate(R.layout.item_event,parent,false),parent,requireActivity,mainViewModel)
    }

    override fun getItemCount(): Int = eventosList.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = eventosList[position]
        holder.render(item)
    }
}