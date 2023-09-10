package com.example.hotel.ui.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.domain.interactor.HotelInteractor
import com.example.hotel.domain.model.AboutHotelItem
import com.example.hotel.domain.model.HotelModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HotelViewModel(private val interactor: HotelInteractor): ViewModel() {

    private val _hotelStateFlow = MutableStateFlow<HotelModel?>(null)
    val hotelStateFlow: StateFlow<HotelModel?> = _hotelStateFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)


    init {
       getAllHotel()
    }

    fun getAllHotel(){
        viewModelScope.launch {
            _hotelStateFlow.value = interactor.getHotel()

        }
    }
}