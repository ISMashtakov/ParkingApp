package com.example.parking.ui.user.reservationCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class ReservationViewModel : ViewModel() {

    private var spotId: String = ""

    private val _spotNumber = MutableStateFlow(0)
    val spotNumber = _spotNumber.asStateFlow()

    fun setSpotData(id: String, number: Int){
        viewModelScope.launch {
            spotId = id
            _spotNumber.emit(number)
        }
    }

    fun reservation(driverId: String, carId: String, startDate: Date, endDate: Date){
        // TODO write reservation logic
    }
}