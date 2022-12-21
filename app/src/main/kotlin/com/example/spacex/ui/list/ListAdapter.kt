package com.example.spacex.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ItemDomain

import com.example.spacex.R
import com.example.spacex.databinding.ItemListBinding


class ListAdapter(
    private val context: Context,
    private val onPicClick: OnPicClick,
    private val planetaryResponse: MutableList<ItemDomain> = mutableListOf()
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return planetaryResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(planetaryResponse[position].url)
            .placeholder(R.drawable.placeholder)
            .centerCrop()
            .into(holder.picture)

        holder.picture.setOnClickListener { onPicClick.onPicClick(position) }
    }


    class ViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val picture = binding.img
    }


    interface OnPicClick {
        fun onPicClick(pos: Int)
    }

}