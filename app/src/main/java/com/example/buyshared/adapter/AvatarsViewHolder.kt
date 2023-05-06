package com.example.buyshared.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.R
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.data.retrofitObjet.Avatar
import com.example.buyshared.databinding.ItemAvatarBinding
import com.example.buyshared.databinding.ItemListBinding
import com.example.buyshared.databinding.ItemTaskBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.Inside.DetailFragment

class AvatarsViewHolder(private val view: View, private val parent: ViewGroup, private val requireActivity: FragmentActivity) :
    RecyclerView.ViewHolder(view) {
    val binding = ItemAvatarBinding.bind(view)

    fun render(avatar:Avatar) {
        Glide.with(requireActivity).load("https://buyshare.onrender.com/images/"+avatar.avatar).into(binding.avatar);
    }
}