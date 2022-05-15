package com.example.parking.data.auth

import com.example.parking.data.cars.CarsApi
import java.lang.Exception

enum class Role {UNAUTHORIZED, ADMIN, USER}

class Authentication(private val carsApi: CarsApi) {
    var login = ""
        private set
    var password = ""
        private set

    var role = Role.UNAUTHORIZED
        private set
    
    suspend fun tryAuth(login: String, password: String){
        return try {
            this.login = login
            this.password = password
            carsApi.getCarsAsync()
            role = Role.USER
        } catch (e: Exception) {
            role = Role.UNAUTHORIZED
        }
    }

}