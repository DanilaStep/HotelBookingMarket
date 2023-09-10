package com.example.booking.customview.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.booking.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TouristView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(
        context,
        attrs
    ) {
    private val view = LayoutInflater.from(context).inflate(R.layout.item_tourist_data, null)
    private val titleText = view.findViewById<TextView>(R.id.tvTouristTitle)
    private val touristIcon = view.findViewById<ImageView>(R.id.ivTouristIcon)
    private val clickableLayout = view.findViewById<FrameLayout>(R.id.llClickTourist)
    private val contentLayout = view.findViewById<LinearLayout>(R.id.llOpenDataTourist)
    private val root = view.findViewById<LinearLayout>(R.id.llTouristContainer)

    private val name = view.findViewById<TextInputEditText>(R.id.tvName)
    private val nameContainer = view.findViewById<TextInputLayout>(R.id.NameContainer)
    private val surName = view.findViewById<TextInputEditText>(R.id.tvSurname)
    private val surNameContainer = view.findViewById<TextInputLayout>(R.id.surNameContainer)
    private val dataOfBirth = view.findViewById<TextInputEditText>(R.id.tvDateOfBirth)
    private val dataOfBirthContainer = view.findViewById<TextInputLayout>(R.id.dateOfBirthContainer)
    private val citizenship = view.findViewById<TextInputEditText>(R.id.tvCitizenship)
    private val citizenshipContainer = view.findViewById<TextInputLayout>(R.id.citizenshipContainer)
    private val passportNumber = view.findViewById<TextInputEditText>(R.id.tvPassportNumber)
    private val passportNumberContainer = view.findViewById<TextInputLayout>(R.id.passportNumberContainer)
    private val passportValidityPeriod = view.findViewById<TextInputEditText>(R.id.tvPassportValidityPeriod)
    private val passportValidityPeriodContainer = view.findViewById<TextInputLayout>(R.id.passportValidityPeriodContainer)

    private val listFields = mapOf(
        name to nameContainer,
        surName to surNameContainer,
        dataOfBirth to dataOfBirthContainer,
        citizenship to citizenshipContainer,
        passportNumber to passportNumberContainer,
        passportValidityPeriod to passportValidityPeriodContainer
    )

    val isExpanded  get() = contentLayout.isVisible


    init {
        initAttrs(attrs)
        initClick()
        addView(view)
    }

    fun checkAllFields(): Boolean{
        var isAllFieldsFilled = true
        listFields.forEach { input ->
            if (input.key.text.isNullOrBlank()){
                input.value.error = input.value.hint
                isAllFieldsFilled = false
            }
        }
        return isAllFieldsFilled
    }

    private fun initClick() {
        clickableLayout.setOnClickListener {
            if (contentLayout.isVisible) {
                animateArrow(ROTATION_DEGREES.unaryMinus(), DEFAULT_ROTATION)
                collapse()
            } else {
                animateArrow(ROTATION_DEGREES, ROTATION_DEGREES)
                expand()
            }
        }
    }

    fun setExpanded(isExpanded: Boolean) {
        if (isInEditMode && isExpanded){
            touristIcon.rotation = ROTATION_DEGREES
        }
        contentLayout.isVisible = isExpanded
        if(isExpanded){
            animateArrow(ROTATION_DEGREES, ROTATION_DEGREES)
        } else {
            animateArrow(ROTATION_DEGREES.unaryMinus(), DEFAULT_ROTATION)
        }
    }

    private fun expand() {
        val matchParentMeasureSpec: Int = MeasureSpec.makeMeasureSpec(
            root.width,
            MeasureSpec.EXACTLY
        )
        val wrapContentMeasureSpec: Int =
            MeasureSpec.makeMeasureSpec(ZERO, MeasureSpec.UNSPECIFIED)
        contentLayout.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight: Int = contentLayout.measuredHeight

        contentLayout.layoutParams.height = ONE
        contentLayout.isVisible = true

        val animation: Animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                contentLayout.layoutParams.height =
                    if (interpolatedTime == ONE.toFloat()) LayoutParams.WRAP_CONTENT
                    else (targetHeight * interpolatedTime).toInt()
                contentLayout.requestLayout()
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration = EXPAND_COLLAPSE_DURATION
        animation.interpolator = FastOutSlowInInterpolator()
        contentLayout.startAnimation(animation)
    }

    private fun collapse() {
        val initialHeight = contentLayout.measuredHeight
        val animation: Animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == ONE.toFloat()) {
                    contentLayout.isGone = true
                } else {
                    contentLayout.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    contentLayout.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration = EXPAND_COLLAPSE_DURATION
        animation.interpolator = FastOutSlowInInterpolator()
        contentLayout.startAnimation(animation)
    }

    private fun animateArrow(rotation: Float, resultRotation: Float) {
        touristIcon.animate().rotationBy(rotation).withEndAction {
            touristIcon.rotation = resultRotation
        }.setDuration(ROTATION_DURATION).setInterpolator(LinearInterpolator()).start()
    }


    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TouristView)
        titleText.text = typedArray.getText(R.styleable.TouristView_titleTourist)
        touristIcon.setImageDrawable(typedArray.getDrawable(R.styleable.TouristView_TouristIcon))
        setExpanded(typedArray.getBoolean(R.styleable.TouristView_isExpanded, false))

        typedArray.recycle()
    }
    fun setIcon(icon: Int){
        touristIcon.setImageResource(icon)
    }

    fun setTitle(title: String){
        titleText.text = title
    }

    companion object {
        private const val ROTATION_DEGREES = 180f
        private const val DEFAULT_ROTATION = 0f
        private const val ROTATION_DURATION = 120L
        private const val ZERO = 0
        private const val ONE = 1
        private const val EXPAND_COLLAPSE_DURATION = 300L
    }
}
