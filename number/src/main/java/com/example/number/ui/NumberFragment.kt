package com.example.number.ui

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.number.R
import com.example.number.databinding.FragmentNumberBinding
import com.example.number.domain.model.RoomItem
import com.example.number.ui.adapter.HotelNumberAdapter
import com.example.number.ui.adapter.NumberAdapter
import kotlinx.coroutines.launch

class NumberFragment : BaseFragment<FragmentNumberBinding, NumberViewModel>(
    FragmentNumberBinding::inflate,
    NumberViewModel::class
), NumberAdapter.ClickListener {

    private val adapterNumber = NumberAdapter(this)
    override fun initialize() {
        binding.rvNumber.adapter = adapterNumber
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.numberStateFlow.collect() { data ->
                data?.let {
                    adapterNumber.submitList(it.rooms)
                }
            }
        }
        onClickBack()
    }


    override fun onClickBooking(id: Int) {
        findNavController().navigate(R.id.action_numberFragment_to_bookingFragment)
    }

    fun onClickBack() {
        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}