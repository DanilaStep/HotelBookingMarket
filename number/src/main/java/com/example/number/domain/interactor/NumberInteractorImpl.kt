package com.example.number.domain.interactor

import com.example.number.data.repositiry.NumberRepository
import com.example.number.domain.model.NumberModel

class NumberInteractorImpl(
    private val repository: NumberRepository
): NumberInteractor {
    override suspend fun getNumber(): NumberModel? {
        return repository.getNumber()
    }
}