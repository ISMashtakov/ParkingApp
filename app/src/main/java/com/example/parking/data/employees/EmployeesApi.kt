package com.example.parking.data.employees

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmployeesApi {

    @GET("./employees/")
    suspend fun getEmployeeAsync(@Query("id") id: String): List<Employee>

    @GET("./employees")
    suspend fun getEmployeesAsync(): List<Employee>

    @POST("./employees")
    suspend fun createEmployeeAsync(@Body employee: Employee): Employee
}