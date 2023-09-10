package com.example.number.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.core.base.BaseHotelAdapter
import com.example.number.databinding.ItemFlexBoxBinding
import com.example.number.databinding.ItemNumberBinding
import com.example.number.domain.model.RoomItem
import com.google.android.flexbox.FlexboxLayoutManager

class FlexBoxAdapter():
    BaseHotelAdapter<String, ItemFlexBoxBinding>(DiffUtilCallback) {
    object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem.length == newItem.length

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

    }


        override fun getBinding(
            inflater: LayoutInflater,
            container: ViewGroup?,
            viewType: Int
        ): ItemFlexBoxBinding {
            return ItemFlexBoxBinding.inflate(inflater, container, false)
        }

        override fun bindViewHolder(holder: ViewHolder, data: String) {
            with(holder.binding){
                tvLine.text = data

            }
        }

}