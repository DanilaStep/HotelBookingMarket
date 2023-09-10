package com.example.hotel.ui.hotel

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.hotel.R
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.domain.model.AboutHotelItem
import com.example.hotel.domain.model.HotelModel
import com.example.hotel.ui.adapter.HotelAdapter
import com.google.android.flexbox.FlexDirection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HotelFragment : BaseFragment<FragmentHotelBinding, HotelViewModel>(
    FragmentHotelBinding::inflate,
    HotelViewModel::class
) {

    private val adapterHotel = HotelAdapter()

    override fun initialize() {
        binding.rvHotel.adapter = adapterHotel

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hotelStateFlow.collect() { data ->
                data?.let {
                    adapterHotel.submitList(it.imageUrls)
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hotelStateFlow.collect() { data ->
                data?.let {
                    hotelData(it)
                }
            }
        }

        showNumber()


    }

    private fun hotelData(data: HotelModel) {
        binding.tvPlaceCity.text = data.adress
        binding.tvPlace.text = data.name
        binding.tvTur.text = data.priceForIt
        binding.tvLine.text = data.aboutHotelItem.peculiarities[0]
        binding.tvWiFi.text = data.aboutHotelItem.peculiarities[1]
        binding.tvAirport.text = data.aboutHotelItem.peculiarities[2]
        binding.tvBeach.text = data.aboutHotelItem.peculiarities[3]
        binding.tvDescription.text = data.aboutHotelItem.description
        val money = data.minimalPrice
        val stringMoney = getString(R.string.money, money.toString())
        binding.tvMoney.text = stringMoney
    }

    fun showNumber() {
        binding.llShow.setOnClickListener {
            findNavController().navigate(R.id.action_hotelFragment_to_numberFragment)
        }
    }

}
