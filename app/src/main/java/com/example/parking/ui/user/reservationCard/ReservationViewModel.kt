package com.example.parking.ui.user.reservationCard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.cars.Car
import com.example.parking.data.cars.CarsApi
import com.example.parking.data.employees.Employee
import com.example.parking.data.employees.EmployeesApi
import com.example.parking.data.reservation.Reservation
import com.example.parking.data.reservation.ReservationsApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.sql.Driver
import java.text.SimpleDateFormat
import java.util.*

class ReservationViewModel(
    private val carsApi: CarsApi,
    private val employeesApi: EmployeesApi,
    private val reservationsApi: ReservationsApi
) : ViewModel() {

    val dateFormat = SimpleDateFormat("yy-MM-dd HH:mm")

    private var spotId: String = ""

    private val _spotNumber = MutableStateFlow(0)
    val spotNumber = _spotNumber.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _onCreated = MutableSharedFlow<Boolean>()
    val onCreated = _onCreated.asSharedFlow()

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars = _cars.asStateFlow()

    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees = _employees.asStateFlow()

    init {
        updateData()
    }

    fun updateData(){
        viewModelScope.launch {
            _isLoading.emit(true)
            _cars.emit(carsApi.getCarsAsync())
            _employees.emit(employeesApi.getEmployeesAsync())
            _isLoading.emit(false)
        }
    }

    fun setSpotData(id: String, number: Int){
        viewModelScope.launch {
            spotId = id
            _spotNumber.emit(number)
        }
    }

    fun reservation(id: String, name: String, number: String, startDate: Date, endDate: Date){
        viewModelScope.launch {
            try {
                _isLoading.emit(true)
                reservationsApi.createReservationAsync(Reservation(
                    id = id,
                    carId = cars.value.find { it.registryNumber == number }!!.id,
                    employeeId = employees.value.find { it.name == name }!!.id,
                    parkingSpotId = spotId,
                    startTime = startDate,
                    endTime = endDate,
                    employee = null
                ))
                _isLoading.emit(false)
                _onCreated.emit(true)
            }
            catch (ex: Exception){
                _isLoading.emit(false)
            }
        }
    }
}