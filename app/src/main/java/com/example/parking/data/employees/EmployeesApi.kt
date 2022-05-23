package com.example.parking.data.employees

import retrofit2.http.GET
import retrofit2.http.Query

interface EmployeesApi {

    @GET("./employees/")
    suspend fun getEmployeeAsync(@Query("id") id: String): List<Employee>

    @GET("./employees")
    suspend fun getEmployeesAsync(): List<Employee>

}