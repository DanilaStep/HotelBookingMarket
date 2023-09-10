package com.example.hotel.di

import com.example.core.base.retrofit.RetrofitFactory
import com.example.hotel.data.api.HotelApi
import com.example.hotel.data.repository.HotelRepository
import com.example.hotel.data.repository.HotelRepositoryImpl
import com.example.hotel.domain.interactor.HotelInteractor
import com.example.hotel.domain.interactor.HotelInteractorImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
class HotelModule {

    @Single
    fun provideHotelApi(retrofit: RetrofitFactory): HotelApi =
        retrofit.getApiInterface(HotelApi::class.java)

    @Single
    fun provideHotelRepository(api: HotelApi): HotelRepository =
        HotelRepositoryImpl(api)

    @Single
    fun provideHotelInteractor(repository: HotelRepository): HotelInteractor =
        HotelInteractorImpl(repository)
}