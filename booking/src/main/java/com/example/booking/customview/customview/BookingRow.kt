package com.example.booking.customview.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.booking.R
import com.example.booking.customview.domain.model.BookingModel


class BookingRow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(
    context,
    attrs
) {
    private val view = LayoutInflater.from(context).inflate(R.layout.item_row_booking, null)
    private val titleText = view.findViewById<TextView>(R.id.tvTitle)
    private val subtitleText = view.findViewById<TextView>(R.id.tvSubtitle)

    init {
        initAttrs(attrs)
        addView(view)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BookingRow)

        setTitleText(typedArray.getString(R.styleable.BookingRow_titleText))
        setSubtitleText(typedArray.getString(R.styleable.BookingRow_subtitleText))

        typedArray.recycle()
    }

    fun setTitleText(data: String?){
        data ?: return
        titleText.text = data
    }
    fun setSubtitleText(data: String?){
        data ?: return
        subtitleText.text = data
    }
}