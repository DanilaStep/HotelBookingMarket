package com.example.booking.customview.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.booking.R

class PaymentRow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(
    context,
    attrs
) {
    private val view = LayoutInflater.from(context).inflate(R.layout.item_payment, null)
    private val titlePayment = view.findViewById<TextView>(R.id.tvTitle)
    private val subtitlePayment = view.findViewById<TextView>(R.id.tvSubtitle)

    init {
        initAttrs(attrs)
        addView(view)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaymentRow)


        setTitlePayment (typedArray.getString(R.styleable.PaymentRow_titlePayment))
        setSubtitlePayment (typedArray.getString(R.styleable.PaymentRow_subtitlePayment))
        setColorSubtitle(typedArray.getColor(R.styleable.PaymentRow_subtitleTextColor,
            ResourcesCompat.getColor(resources, R.color.black, context.theme)))

        typedArray.recycle()
    }
    fun setTitlePayment(data: String?){
        data ?: return
        titlePayment.text = data
    }
    fun setSubtitlePayment(data: String?){
        data ?: return
        subtitlePayment.text = data
    }
    fun setColorSubtitle(color: Int?){
        color ?: return
        subtitlePayment.setTextColor(color)
    }
//    fun setColorSubtitle(resId: Int){
//        setColorSubtitle(color = ResourcesCompat.getColor(resources, resId, context.theme))
//
//    }
}