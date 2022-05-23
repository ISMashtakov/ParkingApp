package com.example.parking.data.reservation

import com.example.parking.data.auth.Authentication
import com.example.parking.data.employees.EmployeesApi
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.data.parking_spot.ParkingSpotsApi

class ReservationRepository(
    private val authentication: Authentication,
    private val reservationsApi: ReservationsApi,
    private val employeesApi: EmployeesApi,
    private val spotsApi: ParkingSpotsApi
    ) {

    suspend fun getAllReservations(): List<Reservation> {
        val reservations = reservationsApi.getReservationsAsync().sortedBy { it.startTime }
        reservations.forEach {
            it.employee = employeesApi.getEmployeeAsync(it.employeeId)[0]
        }
        return reservations
    }

    suspend fun getReservationsForUser(name: String): List<Reservation> {
        return getAllReservations().filter { it -> it.employee?.name == name }
    }

    suspend fun getReservationsForCurrentUser(): List<Reservation> {
        val name = authentication.login
        return getAllReservations().filter { it -> it.employee?.name == name }
    }

    suspend fun getSpotsForCurrentUser(): List<ParkingSpot> {
        return getReservationsForCurrentUser().map { it -> spotsApi.getSpotAsync(it.parkingSpotId)[0] }
    }
}