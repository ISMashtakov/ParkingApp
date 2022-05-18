package com.example.parking.ui.user

import com.example.parking.ui.user.reservation.UserReservationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel<UserReservationViewModel> {
        UserReservationViewModel(
            parkingSpotsAdapter = get()
        )
    }
}