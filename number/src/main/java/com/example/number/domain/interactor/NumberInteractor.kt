package com.example.number.domain.interactor

import com.example.number.domain.model.NumberModel

interface NumberInteractor {
    suspend fun getNumber(): NumberModel?
}