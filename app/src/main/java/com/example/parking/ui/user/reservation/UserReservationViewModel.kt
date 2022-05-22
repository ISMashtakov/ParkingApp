package com.example.parking.ui.user.reservation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.general.ParkingSpotsAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class UserReservationViewModel(val parkingSpotsAdapter: ParkingSpotsAdapter,
                               private val parkingSpotsApi: ParkingSpotsApi) : ViewModel() {

    private var spots: List<ParkingSpot> = emptyList()
        set(newValue){
            field = newValue
            parkingSpotsAdapter.spots = newValue.sortedBy { it.parkingNumber }
        }

    private val _filterIsEnabled = MutableStateFlow(false)
    val filterIsEnabled = _filterIsEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                spots = parkingSpotsApi.getSpotsAsync()
            }
            catch (ex: Exception) {

            }
        }
    }

    fun setFilterSettings(bundle: Bundle){
        viewModelScope.launch {
            _filterIsEnabled.emit(bundle.getBoolean("enabled"))
        }
    }

}