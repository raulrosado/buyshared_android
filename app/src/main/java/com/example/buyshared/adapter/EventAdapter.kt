package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.EventsEntity

class EventAdapter(private val eventosList: List<EventsEntity> ) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(layoutInflater.inflate(R.layout.item_event,parent,false),parent)
    }

    override fun getItemCount(): Int = eventosList.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = eventosList[position]
        holder.render(item)
    }
}