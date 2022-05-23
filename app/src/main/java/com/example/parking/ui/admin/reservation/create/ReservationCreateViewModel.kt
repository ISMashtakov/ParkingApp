package com.example.parking.ui.admin.reservation.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ReservationCreateViewModel(
    private val spotsApi: ParkingSpotsApi
) : ViewModel() {

    private val _isCreating = MutableStateFlow(false)
    val isCreating = _isCreating.asStateFlow()

    private val _onCreated = MutableSharedFlow<Boolean>()
    val onCreated = _onCreated.asSharedFlow()

    fun createSpot(id: String, number: Int){
        viewModelScope.launch {
            try {
                _isCreating.emit(true)
                spotsApi.createSpotAsync(ParkingSpot(id, number, true))
                _isCreating.emit(false)
                _onCreated.emit(true)
            }
            catch (ex: Exception){
                _isCreating.emit(false)
            }
        }
    }
}