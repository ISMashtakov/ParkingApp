package com.example.parking.ui.admin

import com.example.parking.ui.admin.reservation.AdminReservationViewModel
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

}