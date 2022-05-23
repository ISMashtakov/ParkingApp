package com.example.parking.ui.user.spots

import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.data.reservation.ReservationRepository
import com.example.parking.general.ParkingSpotsAdapter
import com.example.parking.ui.user.reservation.UserReservationViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class UserSpotsViewModel(
    parkingSpotsAdapter: ParkingSpotsAdapter,
    parkingSpotsApi: ParkingSpotsApi,
    private val reservationRepository: ReservationRepository
) : UserReservationViewModel(parkingSpotsAdapter, parkingSpotsApi) {

    init {
        viewModelScope.launch {
            try {
                spots = reservationRepository.getSpotsForCurrentUser()
            } catch (ex: Exception) {
            }
        }
    }
}