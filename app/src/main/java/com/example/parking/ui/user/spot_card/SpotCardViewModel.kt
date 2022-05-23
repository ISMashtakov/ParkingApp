package com.example.parking.ui.user.spot_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.reservation.ReservationRepository
import com.example.parking.general.ReservationAdapter
import kotlinx.coroutines.launch

class SpotCardViewModel(
    val reservationAdapter: ReservationAdapter,
    private val reservationRepository: ReservationRepository
) : ViewModel() {
    var spotId: String = ""
        set(newValue) {
            field = newValue
            updateReservations()
        }

    var spotNumber: Int = 0

    private fun updateReservations(){
        viewModelScope.launch {
            reservationAdapter.reservations = reservationRepository.getAllReservations()
        }
    }
}