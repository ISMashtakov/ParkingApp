package com.example.parking.ui.user.spot_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.employees.EmployeesApi
import com.example.parking.data.reservation.ReservationsApi
import com.example.parking.general.ReservationAdapter
import kotlinx.coroutines.launch

class SpotCardViewModel(
    val reservationAdapter: ReservationAdapter,
    private val reservationsApi: ReservationsApi,
    private val employeesApi: EmployeesApi
) : ViewModel() {
    var spotId: String = ""
        set(newValue) {
            field = newValue
            updateReservations()
        }

    var spotNumber: Int = 0

    private fun updateReservations(){
        viewModelScope.launch {
            val reservations = reservationsApi.getReservationsAsync().sortedBy { it.startTime }
            reservations.forEach{
                it.employee = employeesApi.getEmployeeAsync(it.employeeId)[0]
            }
            reservationAdapter.reservations = listOf(reservations[0], reservations[0], reservations[0], reservations[0])
        }
    }
}