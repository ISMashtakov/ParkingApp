package com.example.parking.ui.user

import com.example.parking.ui.user.reservation.UserReservationViewModel
import com.example.parking.ui.user.spot_card.SpotCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel<UserReservationViewModel> {
        UserReservationViewModel(
            parkingSpotsAdapter = get(),
            parkingSpotsApi = get()
        )
    }

    viewModel<SpotCardViewModel> {
        SpotCardViewModel(
            reservationAdapter = get(),
            reservationsApi = get(),
            employeesApi = get()
        )
    }
}