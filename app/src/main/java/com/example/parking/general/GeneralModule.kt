package com.example.parking.general

import android.content.Context

import org.koin.dsl.module

val generalModule = module {
    factory <ParkingSpotsAdapter> {
        ParkingSpotsAdapter(get() as Context)
    }

    factory <ReservationAdapter> {
        ReservationAdapter(get() as Context)
    }

    factory <CarsAdapter> {
        CarsAdapter(get() as Context)
    }

    factory <EmployeesAdapter> {
        EmployeesAdapter()
    }
}