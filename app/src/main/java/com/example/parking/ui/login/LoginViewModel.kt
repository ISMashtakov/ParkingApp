package com.example.parking.ui.login

import androidx.lifecycle.ViewModel
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

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _onAuth = MutableSharedFlow<Role>()
    val onAuth = _onAuth.asSharedFlow()

    fun runCoroutine( coroutine: suspend () -> Unit){
        viewModelScope.launch {
            coroutine()
        }
    }

    fun signIn(login: String, password: String){
        runCoroutine {
            _isLoading.emit(true)
            authentication.tryAuth(login, password)
            _isLoading.emit(false)
            _onAuth.emit(authentication.role)
        }

    }
}