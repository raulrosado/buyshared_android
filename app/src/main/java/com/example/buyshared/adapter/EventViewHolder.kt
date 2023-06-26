package com.example.buyshared.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.R
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.databinding.ItemEventBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.Inside.DetailFragment
import com.example.buyshared.ui.Fragment.Inside.EventDetailFragment
import com.example.buyshared.ui.MainViewModel

class EventViewHolder(
    private val view: View,
    private val parent: ViewGroup,
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.ViewHolder(view) {
    val binding = ItemEventBinding.bind(view)
    val replaceFragment = ReplaceFragment()
    lateinit var fragmentTransaction: FragmentTransaction
    val tinyDB = TinyDB(requireActivity)

    fun render(eventModel: EventsEntity) {
        binding.itemEventNombre.text = eventModel.nombre
        binding.itemCantArticulos.text = eventModel.cant.toString()
        binding.progressBar.max = eventModel.cant
        binding.progressBar.progress = eventModel.taskcomplet
        fragmentTransaction = requireActivity.supportFragmentManager.beginTransaction()

        Log.v(
            "buysharedLog",
            tinyDB.getString("server") + eventModel.bg + "-----" + eventModel.cant + "********" + eventModel.taskcomplet
        )
        Glide.with(parent).load(tinyDB.getString("server") + eventModel.bg)
            .into(binding.itemImgEvent);

        val recyclerAvatarsList = binding.recyclerAvatarEvents
        recyclerAvatarsList.layoutManager =
            LinearLayoutManager(requireActivity, LinearLayoutManager.HORIZONTAL, false)

        val avatarsList = mainViewModel.loadAvatarDBByIdEvent(eventModel._id)
        Log.v("buySharedLog", "cantidad avatar Event" + avatarsList.size)
        recyclerAvatarsList.adapter = AvatarAdapter(avatarsList, requireActivity)

        binding.cardEvent.setOnClickListener {
            Log.v("buysharedLog", "lista seleccionada:" + eventModel.nombre)
            tinyDB.putString("eventSel", eventModel._id)
            tinyDB.putString("typeSelect", "event")
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                EventDetailFragment(),
                fragmentTransaction
            )
        }
    }
}