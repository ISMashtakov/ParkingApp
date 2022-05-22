package com.example.parking.ui.user.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.general.ParkingSpotsAdapter
import kotlinx.coroutines.launch
import java.lang.Exception

class UserReservationViewModel(val parkingSpotsAdapter: ParkingSpotsAdapter,
                               private val parkingSpotsApi: ParkingSpotsApi) : ViewModel() {

    private var spots: List<ParkingSpot> = emptyList()
        set(newValue){
            field = newValue
            parkingSpotsAdapter.spots = newValue.sortedBy { it.parkingNumber }
        }

    init {
        viewModelScope.launch {
            try {
                spots = parkingSpotsApi.getSpotsAsync()
            }
            catch (ex: Exception) {

            }
        }
    }
}