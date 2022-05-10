package com.example.parking.data.cars

import android.content.Context
import com.example.parking.ParkingApp
import org.koin.dsl.module

val carsModule = module {
    single<CarsApi> {
        val retrofit = ((get() as Context).applicationContext as ParkingApp).retrofit
        retrofit.create(CarsApi::class.java)
    }
}