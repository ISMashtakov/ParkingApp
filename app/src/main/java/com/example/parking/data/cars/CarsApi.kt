package com.example.parking.data.cars

import retrofit2.http.GET

interface CarsApi {

    @GET("./cars")
    suspend fun getCarsAsync(): List<Car>
}