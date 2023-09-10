package com.example.number.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.core.base.BaseHotelAdapter
import com.example.number.databinding.ItemNumberBinding
import com.example.number.domain.model.NumberModel
import com.example.number.domain.model.RoomItem
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class NumberAdapter(private val listener: ClickListener) :
    BaseHotelAdapter<RoomItem, ItemNumberBinding>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<RoomItem>() {
        override fun areItemsTheSame(
            oldItem: RoomItem,
            newItem: RoomItem
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RoomItem,
            newItem: RoomItem
        ) = oldItem == newItem


    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewType: Int
    ): ItemNumberBinding {
        return ItemNumberBinding.inflate(inflater, container, false)
    }

    override fun bindViewHolder(holder: ViewHolder, data: RoomItem) {
        val adapter = HotelNumberAdapter()
        val adapterFlex = FlexBoxAdapter()
        with(holder.binding) {
            tvNumber.text = data.name
            tvTur.text = data.price_per
            val price = data.price
            tvMoney.text = "$price ₽"
            rvFlexBox.adapter = adapterFlex
            val lm = FlexboxLayoutManager(root.context)
            lm.flexWrap = FlexWrap.WRAP
            rvFlexBox.layoutManager = lm



            adapterFlex.submitList(data.peculiarities)
            rvImage.adapter = adapter
            adapter.submitList(data.image_urls)
            llClickBooking.setOnClickListener {
                listener.onClickBooking(data.id)
            }
        }

    }

    interface ClickListener {
        fun onClickBooking(id: Int)
    }
}