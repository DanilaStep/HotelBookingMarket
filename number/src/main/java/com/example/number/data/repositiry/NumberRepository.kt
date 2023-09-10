package com.example.number.data.repositiry

import com.example.number.domain.model.NumberModel

interface NumberRepository {
    suspend fun getNumber(): NumberModel?
}