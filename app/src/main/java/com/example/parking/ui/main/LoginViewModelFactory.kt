package com.example.parking.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parking.ParkingApp

class LoginViewModelFactory(private val parkingApp: ParkingApp): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(parkingApp.carsApi) as T
    }
}