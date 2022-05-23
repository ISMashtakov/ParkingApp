package com.example.parking.data.parking_spot

import retrofit2.http.GET
import retrofit2.http.Query

interface ParkingSpotsApi {

    @GET("./parkingSpots")
    suspend fun getSpotsAsync(): List<ParkingSpot>

    @GET("./parkingSpots/")
    suspend fun getSpotAsync(@Query("id") id: String): List<ParkingSpot>
}