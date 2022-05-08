package com.example.parking.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun signIn(login: String, password: String){
        Log.e("Sign", "$login $password")
    }
}