package com.example.booking.customview.di

import com.example.booking.customview.data.api.BookingApi
import com.example.booking.customview.data.repository.BookingRepository
import com.example.booking.customview.data.repository.BookingRepositoryImpl
import com.example.booking.customview.domain.interactor.BookingInteractor
import com.example.booking.customview.domain.interactor.BookingInteractorImpl
import com.example.core.base.retrofit.RetrofitFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class BookingModule {

    @Single
    fun provideBookingApi(retrofit: RetrofitFactory): BookingApi =
        retrofit.getApiInterface(BookingApi::class.java)

    @Single
    fun provideBookingRepository(api: BookingApi): BookingRepository =
        BookingRepositoryImpl(api)
    @Single
    fun provideBookingInteractor(repository: BookingRepository): BookingInteractor =
        BookingInteractorImpl(repository)
}