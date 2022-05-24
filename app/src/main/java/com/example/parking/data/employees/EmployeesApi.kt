package com.example.parking.data.employees

import retrofit2.http.*

interface EmployeesApi {

    @GET("employees/{id}")
    suspend fun getEmployeeAsync(@Path("id") id: String): Employee

    @GET("./employees")
    suspend fun getEmployeesAsync(): List<Employee>

    @POST("./employees")
    suspend fun createEmployeeAsync(@Body employee: Employee): Employee
}