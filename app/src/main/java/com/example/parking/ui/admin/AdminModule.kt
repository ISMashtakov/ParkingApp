package com.example.parking.ui.admin

import com.example.parking.ui.admin.cars.CarsViewModel
import com.example.parking.ui.admin.cars.create.CarsCreateViewModel
import com.example.parking.ui.admin.reservation.AdminReservationViewModel
import com.example.parking.ui.admin.reservation.create.ReservationCreateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adminModule = module {
    viewModel<AdminViewModel> {
        AdminViewModel(
        )
    }

    viewModel<AdminReservationViewModel> {
        AdminReservationViewModel(
            parkingSpotsAdapter = get(),
            parkingSpotsApi = get()
        )
    }

    viewModel<ReservationCreateViewModel> {
        ReservationCreateViewModel(
            spotsApi = get()
        )
    }

    viewModel<CarsViewModel> {
        CarsViewModel(
            carsAdapter = get(),
            carsApi = get()
        )
    }

    viewModel<CarsCreateViewModel> {
        CarsCreateViewModel(
            carsApi = get()
        )
    }

}