package com.example.buyshared.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity
import com.example.buyshared.databinding.ItemListBinding
import com.example.buyshared.databinding.ItemTaskBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.Inside.DetailFragment

class TasksViewHolder(private val view: View, private val parent: ViewGroup, private val requireActivity: FragmentActivity) :
    RecyclerView.ViewHolder(view) {
    val binding = ItemTaskBinding.bind(view)
    val replaceFragment = ReplaceFragment()
    lateinit var fragmentTransaction: FragmentTransaction
    val tinyDB = TinyDB(requireActivity)

    fun render(taskModel:TaskEntity) {
        binding.tareaText.text = taskModel.texto
        if(taskModel.estado === 2){
            binding.checkBoxComplet.isChecked = true
            binding.contrairLayout.setBackgroundColor(Color.parseColor("#E9FAEA"))
        }else{
            binding.checkBoxComplet.isChecked = false
        }
    }
}