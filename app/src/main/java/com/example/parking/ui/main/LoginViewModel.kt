package com.example.parking.ui.main

import androidx.lifecycle.ViewModel
import com.example.parking.data.auth.Authentication
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    val authentication: Authentication
    ) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun runCoroutine(coroutine: suspend () -> Unit){
        viewModelScope.launch {
            withContext(IO) {coroutine()}
        }
    }

    fun signIn(login: String, password: String){
        runCoroutine {
            _isLoading.emit(true)
            authentication.tryAuth(login, password)
            _isLoading.emit(false)
        }

    }
}