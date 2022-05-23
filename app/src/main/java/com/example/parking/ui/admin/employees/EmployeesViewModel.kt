package com.example.parking.ui.admin.employees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.cars.Car
import com.example.parking.data.employees.Employee
import com.example.parking.data.employees.EmployeesApi
import com.example.parking.general.EmployeesAdapter
import kotlinx.coroutines.launch
import java.lang.Exception

class EmployeesViewModel(
    val employeesAdapter: EmployeesAdapter,
    private val employeesApi: EmployeesApi
) : ViewModel() {
    private var employees: List<Employee> = emptyList()
        set(newValue) {
            field = newValue
            employeesAdapter.employees = newValue.sortedBy { it.name }
        }

    init {
        updateEmployees()
    }

    fun updateEmployees(){
        viewModelScope.launch {
            try {
                employees = employeesApi.getEmployeesAsync()
            } catch (ex: Exception) {

            }
        }
    }
}