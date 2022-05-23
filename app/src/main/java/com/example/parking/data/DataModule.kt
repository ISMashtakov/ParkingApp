package com.example.parking.data

import android.content.Context
import com.example.parking.ParkingApp
import com.example.parking.data.auth.Authentication
import com.example.parking.data.cars.CarsApi
import com.example.parking.data.employees.EmployeesApi
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.data.reservation.ReservationRepository
import com.example.parking.data.reservation.ReservationsApi

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

    single<ReservationsApi> {
        val retrofit = ((get() as Context).applicationContext as ParkingApp).retrofit
        retrofit.create(ReservationsApi::class.java)
    }

    single<ParkingSpotsApi> {
        val retrofit = ((get() as Context).applicationContext as ParkingApp).retrofit
        retrofit.create(ParkingSpotsApi::class.java)
    }

    single<EmployeesApi> {
        val retrofit = ((get() as Context).applicationContext as ParkingApp).retrofit
        retrofit.create(EmployeesApi::class.java)
    }

    single<ReservationRepository> {
        ReservationRepository(
            authentication = get(),
            reservationsApi = get(),
            employeesApi = get(),
            spotsApi = get()
        )
    }
}
