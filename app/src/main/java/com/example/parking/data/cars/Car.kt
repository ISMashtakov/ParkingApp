package com.example.parking.data.cars

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("length")
    val length: Int,
    @SerializedName("wight")
    val weight: Int,
    @SerializedName("registryNumber")
    val registryNumber: String,
)