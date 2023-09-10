package com.example.number.data.repositiry

import com.example.number.data.api.NumberApi
import com.example.number.data.mapper.NumberMapper.map
import com.example.number.domain.model.NumberModel

class NumberRepositoryImpl (
    private val api: NumberApi
        ): NumberRepository{
    override suspend fun getNumber(): NumberModel? {
        return api.getNumber().body().map()
    }
}