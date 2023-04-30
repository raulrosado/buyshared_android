package com.example.buyshared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.databinding.ItemEventBinding
import kotlinx.coroutines.flow.callbackFlow

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemEventBinding.bind(view)

    fun render(eventModel: EventsEntity){
        binding.itemEventNombre.text = eventModel.nombre
        binding.itemCantArticulos.text = eventModel.cant.toString()
        binding.progressBar.max = eventModel.cant
        binding.progressBar.progress = eventModel.complet

    }
}