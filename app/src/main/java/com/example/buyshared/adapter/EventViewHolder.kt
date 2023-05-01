package com.example.buyshared.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.databinding.ItemEventBinding

class EventViewHolder(private val view: View, private val parent: ViewGroup) : RecyclerView.ViewHolder(view) {
    val binding = ItemEventBinding.bind(view)

    fun render(eventModel: EventsEntity){
        binding.itemEventNombre.text = eventModel.nombre
        binding.itemCantArticulos.text = eventModel.cant.toString()
        binding.progressBar.max = eventModel.cant
        binding.progressBar.progress = eventModel.taskcomplet
        Log.v("buysharedLog","https://buyshare.onrender.com/images/"+eventModel.bg + "-----" + eventModel.cant + "********" + eventModel.taskcomplet)
        Glide.with(parent).load("https://buyshare.onrender.com/images/"+eventModel.bg).into(binding.itemImgEvent);
    }
}