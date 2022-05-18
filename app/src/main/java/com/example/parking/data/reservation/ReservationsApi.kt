package com.example.parking.data.reservation

import retrofit2.http.GET

interface ReservationsApi {

    @GET("./reservations")
    suspend fun getReservationsAsync(): List<Reservation>
}