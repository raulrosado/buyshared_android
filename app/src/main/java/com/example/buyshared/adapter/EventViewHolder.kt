package com.example.buyshared.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyshared.R
import com.example.buyshared.data.model.EventsEntity
import com.example.buyshared.databinding.ItemEventBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.Inside.DetailFragment
import com.example.buyshared.ui.Fragment.Inside.EventDetailFragment

class EventViewHolder(
    private val view: View,
    private val parent: ViewGroup,
    private val requireActivity: FragmentActivity
) : RecyclerView.ViewHolder(view) {
    val binding = ItemEventBinding.bind(view)
    val replaceFragment = ReplaceFragment()
    lateinit var fragmentTransaction: FragmentTransaction
    val tinyDB = TinyDB(requireActivity)

    fun render(eventModel: EventsEntity){
        binding.itemEventNombre.text = eventModel.nombre
        binding.itemCantArticulos.text = eventModel.cant.toString()
        binding.progressBar.max = eventModel.cant
        binding.progressBar.progress = eventModel.taskcomplet
        fragmentTransaction = requireActivity.supportFragmentManager.beginTransaction()

        Log.v("buysharedLog","https://buyshare.onrender.com/images/"+eventModel.bg + "-----" + eventModel.cant + "********" + eventModel.taskcomplet)
        Glide.with(parent).load("https://buyshare.onrender.com/images/"+eventModel.bg).into(binding.itemImgEvent);

        binding.cardEvent.setOnClickListener {
            Log.v("buysharedLog", "lista seleccionada:" + eventModel.nombre)
            tinyDB.putString("eventSel",eventModel._id)
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                EventDetailFragment(),
                fragmentTransaction
            )
        }
    }
}