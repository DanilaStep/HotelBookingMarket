package com.example.hotel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.core.base.BaseHotelAdapter
import com.example.hotel.databinding.ItemHotelBinding
import com.example.hotel.domain.model.HotelModel

class HotelAdapter(): BaseHotelAdapter<String, ItemHotelBinding>(DiffUtilCallback){

    object DiffUtilCallback: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ) = oldItem.length == newItem.length

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ) = oldItem == newItem

    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewType: Int
    ): ItemHotelBinding {
        return ItemHotelBinding.inflate(inflater, container, false)
    }

    override fun bindViewHolder(holder: ViewHolder, data: String) {
        with(holder.binding){
         Glide.with(ivHotel)
             .load(data)
             .into(ivHotel)
        }
    }
}