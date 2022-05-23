package com.example.parking.ui.admin.reservation

import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.general.ParkingSpotsAdapter
import com.example.parking.ui.user.reservation.UserReservationViewModel

class AdminReservationViewModel(
    parkingSpotsAdapter: ParkingSpotsAdapter,
    parkingSpotsApi: ParkingSpotsApi
) : UserReservationViewModel(parkingSpotsAdapter, parkingSpotsApi) {
}