package com.example.buyshared.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.databinding.ItemEventBinding
import com.example.buyshared.databinding.ItemListBinding

class ListsViewHolder(private val view: View, private val parent: ViewGroup) : RecyclerView.ViewHolder(view) {
    val binding = ItemListBinding.bind(view)

    fun render(listModel: ListsEntity){
        binding.itemListNombre.text = listModel.nombre
        binding.itemCantArticulos.text = listModel.cant.toString()
    }
}