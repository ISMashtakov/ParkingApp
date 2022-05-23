package com.example.parking.data.auth

import com.example.parking.data.cars.CarsApi
import java.lang.Exception

enum class Role { UNAUTHORIZED, ADMIN, USER }

class Authentication(private val carsApi: CarsApi) {
    var login = "admin"
        private set
    var password = "password"
        private set

    var role = Role.ADMIN
        private set

    suspend fun tryAuth(login: String, password: String): Role {
        try {
            this.login = login
            this.password = password
            carsApi.getCarsAsync()
            role = Role.USER
        } catch (e: Exception) {
            role = Role.UNAUTHORIZED
        }

        return role
    }

}