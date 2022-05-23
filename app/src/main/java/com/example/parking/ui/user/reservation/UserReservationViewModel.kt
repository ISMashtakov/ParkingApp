package com.example.parking.ui.user.reservation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi
import com.example.parking.general.ParkingSpotsAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

open class UserReservationViewModel(
    val parkingSpotsAdapter: ParkingSpotsAdapter,
    private val parkingSpotsApi: ParkingSpotsApi
) : ViewModel() {

    protected var spots: List<ParkingSpot> = emptyList()
        set(newValue) {
            field = newValue
            parkingSpotsAdapter.spots = newValue.sortedBy { it.parkingNumber }
        }

    private val filterIsEnabledMutable = MutableStateFlow(false)
    val filterIsEnabled = filterIsEnabledMutable.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                spots = parkingSpotsApi.getSpotsAsync()
            } catch (ex: Exception) {

            }
        }
    }

    fun setFilterSettings(bundle: Bundle) {
        viewModelScope.launch {
            filterIsEnabledMutable.emit(bundle.getBoolean("enabled"))
        }
    }

}