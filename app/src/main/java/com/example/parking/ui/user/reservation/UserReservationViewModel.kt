package com.example.parking.ui.user.reservation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.general.ClickListener
import com.example.parking.general.ParkingSpotsAdapter
import kotlinx.coroutines.launch
import java.lang.Exception

class UserReservationViewModel(val parkingSpotsAdapter: ParkingSpotsAdapter,
                               private val parkingSpotsApi: ParkingSpotsApi) : ViewModel() {
    init {
        viewModelScope.launch {
            try {
                parkingSpotsAdapter.spots = parkingSpotsApi.getSpotsAsync()
            }
            catch (ex: Exception) {

            }
        }
    }
}