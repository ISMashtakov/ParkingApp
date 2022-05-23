package com.example.parking.data.cars

import com.example.parking.data.parking_spot.ParkingSpot
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarsApi {

    @GET("./cars")
    suspend fun getCarsAsync(): List<Car>

    @POST("./cars")
    suspend fun createCarAsync(@Body car: Car): Car
}