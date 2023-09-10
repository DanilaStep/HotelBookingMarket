package com.example.booking.customview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking.customview.domain.interactor.BookingInteractor
import com.example.booking.customview.domain.model.BookingModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class BookingViewModel(private val interactor: BookingInteractor): ViewModel() {
private val _bookingStateFlow = MutableStateFlow<BookingModel?>(null)
    val bookingStateFlow: StateFlow<BookingModel?> = _bookingStateFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)


    init {
        getAllBooking()

    }
    fun getAllBooking(){
        viewModelScope.launch {
            _bookingStateFlow.value = interactor.getBooking()
        }
    }
}