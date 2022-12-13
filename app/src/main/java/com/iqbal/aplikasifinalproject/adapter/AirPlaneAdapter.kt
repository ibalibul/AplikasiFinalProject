package com.iqbal.aplikasifinalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iqbal.aplikasifinalproject.databinding.ItemAirplaneBinding
import com.iqbal.aplikasifinalproject.model.ResponseDataFilmItem

class AirPlaneAdapter(var listairPlane: List<ResponseDataFilmItem>?) : RecyclerView.Adapter<AirPlaneAdapter.ViewHolder>() {

    class ViewHolder (var binding : ItemAirplaneBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemAirplaneBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.namePlaces.text = listairPlane!![position].name
        holder.binding.lokasi.text = listairPlane!![position].director
        holder.binding.Description.text = listairPlane!![position].description
        Glide.with(holder.itemView).load(listairPlane!![position].image).into(holder.binding.imgPlace)
    }

    override fun getItemCount(): Int {
        return listairPlane!!.size
    }
}