package com.example.parking.ui.admin.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.cars.Car
import com.example.parking.data.cars.CarsApi
import com.example.parking.general.CarsAdapter
import kotlinx.coroutines.launch
import java.lang.Exception

class CarsViewModel(
    val carsAdapter: CarsAdapter,
    val carsApi: CarsApi
    ) : ViewModel() {

    private var cars: List<Car> = emptyList()
        set(newValue) {
            field = newValue
            carsAdapter.cars = newValue.sortedBy { it.model }
        }

    init {
        updateCars()
    }

    fun updateCars(){
        viewModelScope.launch {
            try {
                cars = carsApi.getCarsAsync()
            } catch (ex: Exception) {

            }
        }
    }
}