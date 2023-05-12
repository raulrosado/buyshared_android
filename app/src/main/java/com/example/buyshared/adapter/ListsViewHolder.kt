package com.example.buyshared.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.databinding.ItemListBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.Inside.DetailFragment

class ListsViewHolder(private val view: View, private val parent: ViewGroup,private val requireActivity: FragmentActivity) :
    RecyclerView.ViewHolder(view) {
    val binding = ItemListBinding.bind(view)
    val replaceFragment = ReplaceFragment()
    lateinit var fragmentTransaction: FragmentTransaction
    val tinyDB = TinyDB(requireActivity)

    fun render(listModel: ListsEntity) {
        binding.itemListNombre.text = listModel.nombre
        binding.itemCantArticulos.text = listModel.cant.toString()
        fragmentTransaction = requireActivity.supportFragmentManager.beginTransaction()

        binding.layoutList.setOnClickListener {
            Log.v("buysharedLog", "lista seleccionada:" + listModel.nombre)
            tinyDB.putString("listSel",listModel._id)
            tinyDB.putString("typeSelect","list")
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                DetailFragment(),
                fragmentTransaction
            )
        }
    }
}