package com.example.number.di

import com.example.core.base.retrofit.RetrofitFactory
import com.example.number.data.api.NumberApi
import com.example.number.data.repositiry.NumberRepository
import com.example.number.data.repositiry.NumberRepositoryImpl
import com.example.number.domain.interactor.NumberInteractor
import com.example.number.domain.interactor.NumberInteractorImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NumberModule {
    @Single
    fun provideNumberApi(retrofit: RetrofitFactory): NumberApi =
        retrofit.getApiInterface(NumberApi::class.java)

    @Single
    fun provideNumberRepository(api: NumberApi): NumberRepository =
        NumberRepositoryImpl(api)

    @Single
    fun provideNumberInteractor(repository: NumberRepository): NumberInteractor =
        NumberInteractorImpl(repository)
}