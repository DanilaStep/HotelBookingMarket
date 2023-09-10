package com.example.number.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.core.base.BaseHotelAdapter
import com.example.number.databinding.ItemImageBinding
import com.example.number.domain.model.RoomItem

class HotelNumberAdapter(): BaseHotelAdapter<String, ItemImageBinding>(DiffUtilCallback){

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
    ): ItemImageBinding {
        return ItemImageBinding.inflate(inflater, container, false)
    }

    override fun bindViewHolder(holder: ViewHolder, data: String) {
        with(holder.binding){
            Glide.with(ivImage)
                .load(data)
                .into(ivImage)
        }
    }
}
