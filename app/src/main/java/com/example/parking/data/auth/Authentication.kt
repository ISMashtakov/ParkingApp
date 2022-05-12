package com.example.parking.data.auth

import com.example.parking.data.cars.CarsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception

enum class Role {UNAUTHORIZED, ADMIN, USER}

class Authentication(private val carsApi: CarsApi) {
    var login = ""
        private set
    var password = ""
        private set


    private val _role = MutableStateFlow<Role>(Role.UNAUTHORIZED)
    val role = _role.asStateFlow()
    
    suspend fun tryAuth(login: String, password: String){
        return try {
            this.login = login
            this.password = password
            carsApi.getCarsAsync()
            _role.emit(Role.USER)
        } catch (e: Exception) {
            _role.emit(Role.UNAUTHORIZED)
        }
    }

}