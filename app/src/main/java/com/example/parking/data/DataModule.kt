package com.example.parking.data

import android.content.Context
import com.example.parking.ParkingApp
import com.example.parking.data.auth.Authentication
import com.example.parking.data.cars.CarsApi

import org.koin.dsl.module

val dataModule = module {
    single<Authentication> {
        Authentication(
            carsApi = get()
        )
    }

    single<CarsApi> {
        val retrofit = ((get() as Context).applicationContext as ParkingApp).retrofit
        retrofit.create(CarsApi::class.java)
    }
}