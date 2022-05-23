package com.example.parking.ui.admin.cars.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.cars.Car
import com.example.parking.data.cars.CarsApi
import com.example.parking.data.parking_spot.ParkingSpot
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class CarsCreateViewModel(val carsApi: CarsApi) : ViewModel() {
    private val _isCreating = MutableStateFlow(false)
    val isCreating = _isCreating.asStateFlow()

    private val _onCreated = MutableSharedFlow<Boolean>()
    val onCreated = _onCreated.asSharedFlow()

    fun createCar(id: String, number: String, model: String, length: Int, weight: Int){
        viewModelScope.launch {
            try {
                _isCreating.emit(true)
                carsApi.createCarAsync(Car(id, model, length, weight, number))
                _isCreating.emit(false)
                _onCreated.emit(true)
            }
            catch (ex: Exception){
                _isCreating.emit(false)
            }
        }
    }
}