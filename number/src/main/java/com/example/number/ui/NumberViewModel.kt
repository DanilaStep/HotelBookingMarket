package com.example.number.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.domain.interactor.NumberInteractor
import com.example.number.domain.model.NumberModel
import com.example.number.domain.model.RoomItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NumberViewModel(private val interactor: NumberInteractor):ViewModel() {
    private val _numberStateFlow = MutableStateFlow<NumberModel?>(null)
val numberStateFlow: StateFlow<NumberModel?> = _numberStateFlow
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

init {
    getAllNumber()
}
    fun getAllNumber(){
        viewModelScope.launch {
            _numberStateFlow.value = interactor.getNumber()
        }
    }

}