package com.example.paid

import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.paid.databinding.FragmentPaidBinding
import java.nio.channels.Pipe

class PaidFragment : BaseFragment<FragmentPaidBinding, PaidViewModel>(
    FragmentPaidBinding::inflate,
    PaidViewModel::class
) {
    override fun initialize() {
        onClickBack()
        onClickSuper()
        val num = (104893..104897).random()

        binding.tvDescription.text = "Подтверждение заказа $num может занять некоторое время " +
                "(от 1 часа до суток). Как только мы получим ответ от туроператора, вам на почту придет уведомление."
    }

    fun onClickBack() {
        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun onClickSuper() {
        binding.llSuper.setOnClickListener {
            findNavController().popBackStack(R.id.hotelFragment, false)
        }
    }
}