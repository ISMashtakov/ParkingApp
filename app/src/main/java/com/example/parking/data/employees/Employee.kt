package com.example.parking.data.employees

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,

)