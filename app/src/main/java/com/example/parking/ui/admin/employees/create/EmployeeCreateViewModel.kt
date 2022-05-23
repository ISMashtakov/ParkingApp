package com.example.parking.ui.admin.employees.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.cars.Car
import com.example.parking.data.employees.Employee
import com.example.parking.data.employees.EmployeesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class EmployeeCreateViewModel(
    private val employeesApi: EmployeesApi
) : ViewModel() {
    private val _isCreating = MutableStateFlow(false)
    val isCreating = _isCreating.asStateFlow()

    private val _onCreated = MutableSharedFlow<Boolean>()
    val onCreated = _onCreated.asSharedFlow()

    fun createEmployee(id: String, name: String){
        viewModelScope.launch {
            try {
                _isCreating.emit(true)
                employeesApi.createEmployeeAsync(Employee(id, name))
                _isCreating.emit(false)
                _onCreated.emit(true)
            }
            catch (ex: Exception){
                _isCreating.emit(false)
            }
        }
    }
}