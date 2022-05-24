package com.example.parking.data.reservation

import com.example.parking.data.cars.Car
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReservationsApi {

    @GET("./reservations")
    suspend fun getReservationsAsync(): List<Reservation>

    @POST("./reservations")
    suspend fun createReservationAsync(@Body reservation: Reservation): Reservation
}