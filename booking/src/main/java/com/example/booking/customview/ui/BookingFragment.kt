package com.example.booking.customview.ui

import android.content.res.Resources
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.customview.customview.TouristView
import com.example.booking.customview.domain.model.BookingModel
import com.example.booking.databinding.FragmentBookingBinding
import com.example.core.base.BaseFragment
import kotlinx.coroutines.launch
import java.time.format.TextStyle

class BookingFragment : BaseFragment<FragmentBookingBinding, BookingViewModel>(
    FragmentBookingBinding::inflate,
    BookingViewModel::class
) {

    override fun initialize() {
        onClickBack()
        onClickBooking()
        clickNewTourist()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookingStateFlow.collect { data ->
                data?.let {
                    fillData(it)
                }
            }
        }

    }

    private fun fillData(data: BookingModel) {
        binding.tvPlace.text = data.hotel_name
        binding.tvPlaceCity.text = data.hotel_adress
        fillBookingRow(data)
        fillPaymentRow(data)
    }

    private fun fillBookingRow(data: BookingModel) {
        binding.brLoot.setSubtitleText(data.departure)
        binding.brCity.setSubtitleText(data.arrival_country)
        binding.brTime.setSubtitleText("${data.tour_date_start} - ${data.tour_date_stop}")
        binding.brNight.setSubtitleText("${data.number_of_nights} ночей")
        binding.brHotel.setSubtitleText(data.hotel_name)
        binding.brNumber.setSubtitleText(data.room)
        binding.brFood.setSubtitleText(data.nutrition)
    }

    private fun fillPaymentRow(data: BookingModel) {
        binding.brTour.setSubtitlePayment(data.tour_price.toString())
        binding.brFuelSurcharge.setSubtitlePayment(data.fuel_charge.toString())
        binding.brServiceFee.setSubtitlePayment(data.service_charge.toString())
        val sumPayment = data.tour_price + data.fuel_charge + data.service_charge
        binding.iPayment.setSubtitlePayment(sumPayment.toString())
        binding.iPayment.setColorSubtitle(
            ResourcesCompat.getColor(
                resources,
                R.color.blue,
                requireContext().theme
            )
        )
        binding.tvNumberSelection.text = "Оплаить $sumPayment ₽"
    }


    private fun onClickBooking() {

        binding.llBookingClick.setOnClickListener {
            if (checkAllTourists()) {
                findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
            }
        }
    }

    private fun checkAllTourists(): Boolean{
        var result = true
        binding.llTourist.children.forEach { tourist ->
            if  ((tourist as TouristView).isExpanded) {
                result = tourist.checkAllFields()
            }
        }
        return result
    }

    private fun clickNewTourist() {

        binding.frAddTourist.setOnClickListener {
           val touristView = TouristView(requireContext()).apply {
                val topMarginDP = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    TOP_MARGIN,
                    resources.displayMetrics
                )
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.topMargin = topMarginDP.toInt()
                this.layoutParams = layoutParams
                this.setIcon(icon = R.drawable.ic_arrow_down)
                setTitle(title = R.string.tourist.toString())
            }
            binding.llTourist.addView(touristView)
        }
    }


    private fun onClickBack() {
        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    companion object
    val TOP_MARGIN = 10F

}