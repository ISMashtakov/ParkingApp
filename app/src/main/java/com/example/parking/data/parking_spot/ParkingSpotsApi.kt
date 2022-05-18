package com.example.parking.data.parking_spot

import retrofit2.http.GET

interface ParkingSpotsApi {

    @GET("./parkingSpots")
    suspend fun getSpotsAsync(): List<ParkingSpot>
}