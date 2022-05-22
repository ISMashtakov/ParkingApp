package com.example.parking.ui.user.spots

import android.util.Log
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
        Log.e("Data", "Hello")
        viewModelScope.launch {
            try {
                Log.e("Data", "start")
                spots = reservationRepository.getSpotsForCurrentUser()
                Log.e("Data", spots.count().toString())
            } catch (ex: Exception) {
                Log.e("Data", ex.toString())
            }
        }
    }
}