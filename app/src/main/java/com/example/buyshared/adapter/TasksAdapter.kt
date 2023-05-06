package com.example.buyshared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buyshared.R
import com.example.buyshared.data.model.ListsEntity
import com.example.buyshared.data.model.TaskEntity

class TasksAdapter(private val lists: List<TaskEntity>, private val requireActivity: FragmentActivity) : RecyclerView.Adapter<TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TasksViewHolder(layoutInflater.inflate(R.layout.item_task,parent,false),parent,requireActivity)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = lists[position]
        holder.render(item)
    }
}