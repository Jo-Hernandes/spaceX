package com.jhernandes.spacex.main.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhernandes.spacex.databinding.ItemLaunchBinding
import com.jhernandes.spacex.model.MissionLaunchModel

class LaunchesAdapter(private val onLaunchSelected: (MissionLaunchModel) -> Unit) :
    RecyclerView.Adapter<LaunchesAdapter.LaunchesItem>() {

    var launches: List<MissionLaunchModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesItem =
        LaunchesItem(
            ItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LaunchesItem, position: Int) =
        holder.bindItem(launches[position])

    override fun getItemCount(): Int = launches.size

    inner class LaunchesItem(private val itemLaunchBinding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(itemLaunchBinding.root) {

        fun bindItem(missionModel: MissionLaunchModel) {
            itemLaunchBinding.launch = missionModel
            itemView.setOnClickListener { onLaunchSelected(missionModel) }
        }
    }
}
