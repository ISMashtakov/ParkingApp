package com.example.parking.ui.user

import com.example.parking.ui.user.reservation.UserReservationViewModel
import com.example.parking.ui.user.reservation.filter.ReservationsFilterViewModel
import com.example.parking.ui.user.reservationCard.ReservationViewModel
import com.example.parking.ui.user.spot_card.SpotCardViewModel
import com.example.parking.ui.user.spots.UserSpotsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel<UserViewModel> {
        UserViewModel(
        )
    }

    viewModel<UserReservationViewModel> {
        UserReservationViewModel(
            parkingSpotsAdapter = get(),
            parkingSpotsApi = get()
        )
    }

    viewModel<SpotCardViewModel> {
        SpotCardViewModel(
            reservationAdapter = get(),
            reservationRepository = get(),
        )
    }

    viewModel<ReservationsFilterViewModel> {
        ReservationsFilterViewModel(
        )
    }

    viewModel<UserSpotsViewModel> {
        UserSpotsViewModel(
            parkingSpotsAdapter = get(),
            parkingSpotsApi = get(),
            reservationRepository = get()
        )
    }

    viewModel<ReservationViewModel> {
        ReservationViewModel(

        )
    }
}