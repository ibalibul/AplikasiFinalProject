package com.iqbal.aplikasifinalproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.academy.flightgoadmin.model.tiket.TiketResponse
import binar.academy.flightgoadmin.model.tiket.TiketResponseItem
import com.bumptech.glide.Glide
import com.iqbal.aplikasifinalproject.databinding.ItemAirplaneBinding
import com.iqbal.aplikasifinalproject.databinding.ItemAvailableFlightBinding

class AirPlaneAdapter(private val context: Context,
                      private var listTiketItem: List<TiketResponseItem>,
                      private val onClick : onClickInterface) : RecyclerView.Adapter<AirPlaneAdapter.ViewHolder>() {

    class ViewHolder (var binding : ItemAvailableFlightBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemAvailableFlightBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val currentList = listTiketItem[position]
            tvDepart.text = currentList.bandaraAsal
            tvArrival.text = currentList.bandaraTujuan
            tvKodeDepart.text = currentList.kodeNegaraAsal
            tvKodeArrival.text = currentList.kodeNegaraTujuan
            tvTimeDepart.text = currentList.depatureTime
            tvFlightType.text = currentList.bentukPenerbangan
            tvPrice.text = buildString {
                append("Rp. ")
                append(currentList.price.toString())
            }
            Glide.with(holder.itemView).load(currentList.imageProduct).timeout(6000).into(vImage)
        }

        holder.binding.root.setOnClickListener {
            onClick.onItemClick(listTiketItem[position])
        }
    }

    override fun getItemCount(): Int {
        return listTiketItem!!.size
    }

    interface onClickInterface{
        fun onItemClick(list: TiketResponseItem)
    }
}