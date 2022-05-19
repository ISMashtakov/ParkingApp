package com.example.parking.data.reservation

import com.example.parking.data.employees.Employee
import com.google.gson.annotations.SerializedName
import java.util.*

data class Reservation(
    @SerializedName("id")
    val id: String,
    @SerializedName("carId")
    val carId: String,
    @SerializedName("employeeId")
    val employeeId: String,
    @SerializedName("parkingSpotId")
    val parkingSpotId: String,
    @SerializedName("startTime")
    val startTime: Date,
    @SerializedName("endTime")
    val endTime: Date,


    var employee: Employee?
)
