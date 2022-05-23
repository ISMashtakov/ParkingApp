package com.example.parking.general

import android.content.Context
import com.example.parking.ParkingApp
import com.example.parking.data.auth.Authentication
import com.example.parking.data.cars.CarsApi
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.data.reservation.ReservationsApi

import org.koin.dsl.module

val generalModule = module {
    factory <ParkingSpotsAdapter> {
        ParkingSpotsAdapter(get() as Context)
    }

    factory <ReservationAdapter> {
        ReservationAdapter(get() as Context)
    }
}