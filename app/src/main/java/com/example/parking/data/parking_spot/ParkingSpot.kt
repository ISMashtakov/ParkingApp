package com.example.parking.data.parking_spot

import com.google.gson.annotations.SerializedName
import java.util.*

data class ParkingSpot(
    @SerializedName("id")
    val id: String,
    @SerializedName("parkingNumber")
    val parkingNumber: Int,
    @SerializedName("isFree")
    val isFree: Boolean,
)
