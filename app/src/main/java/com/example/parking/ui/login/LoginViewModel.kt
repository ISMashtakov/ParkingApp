package com.example.parking.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.auth.Authentication
import com.example.parking.data.auth.Role
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val authentication: Authentication
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _onAuth = MutableSharedFlow<Role>()
    val onAuth = _onAuth.asSharedFlow()

    fun signIn(login: String, password: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            val role = authentication.tryAuth(login, password)
            _isLoading.emit(false)
            _onAuth.emit(role)
        }
    }
}