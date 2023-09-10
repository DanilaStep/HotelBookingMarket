package com.example.hotel.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.hotel.R


class Row @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(
    context,
    attrs
) {
    private val view = LayoutInflater.from(context).inflate(R.layout.item_row, null)

    private val leftIcon = view.findViewById<ImageView>(R.id.ivLeftIcon)
    private val titleText = view.findViewById<TextView>(R.id.tvTitle)
    private val subtitleText = view.findViewById<TextView>(R.id.tvSubtitle)

    init {
        initAttrs(attrs)
        addView(view)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Row)

        titleText.text = typedArray.getText(R.styleable.Row_titleText)
        subtitleText.text = typedArray.getText(R.styleable.Row_subtitleText)
        leftIcon.setImageDrawable(typedArray.getDrawable(R.styleable.Row_leftIcon))

        typedArray.recycle()
    }
}