package com.example.buyshared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.databinding.ItemAvatarBinding
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.MainViewModel

class AvatarsViewHolder(
    private val view: View,
    private val parent: ViewGroup,
    private val requireActivity: FragmentActivity
) :
    RecyclerView.ViewHolder(view) {
    val binding = ItemAvatarBinding.bind(view)
    val tinyDB = TinyDB(requireActivity)

    fun render(avatar: Avatar) {
        Glide.with(requireActivity).load(tinyDB.getString("server") +"images/"+ avatar.avatar)
            .into(binding.avatar);
    }
}