package com.example.parking.data.cars

import io.reactivex.Single
import retrofit2.http.GET

interface CarsApi {

    @GET("./cars")
    fun getCars(): Single<List<Car>>
}