package com.example.hotel.app

import android.app.Application
import com.example.booking.customview.di.BookingModule
import com.example.booking.customview.ui.BookingViewModel
import com.example.core.base.retrofit.RetrofitFactory
import com.example.hotel.di.HotelModule
import com.example.hotel.ui.hotel.HotelViewModel
import com.example.number.di.NumberModule
import com.example.number.ui.NumberViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.scope.get
import org.koin.dsl.module
import kotlin.math.sin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private val retrofitModule = module {
        factory { RetrofitFactory() }
    }
    private val bookingModule = module {
        BookingModule().apply {
            single { provideBookingApi(get()) }
            single { provideBookingRepository(get()) }
            single { provideBookingInteractor(get()) }
            viewModel { BookingViewModel(get()) }

        }
    }
    private val hotelModule = module {
        HotelModule().apply {
            single { provideHotelApi(get()) }
            single { provideHotelRepository(get())}
            single { provideHotelInteractor(get()) }
            viewModel{ HotelViewModel(get())}
        }
    }
    private val numberModule = module {
        NumberModule().apply {
            single { provideNumberApi(get()) }
            single { provideNumberRepository(get()) }
            single { provideNumberInteractor(get()) }
            viewModel{NumberViewModel(get())}

        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                retrofitModule,
                hotelModule,
                bookingModule,
                numberModule
            )
        }
    }
}