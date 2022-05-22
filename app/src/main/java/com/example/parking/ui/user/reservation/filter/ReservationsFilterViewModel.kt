package com.example.parking.ui.user.reservation.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReservationsFilterViewModel : ViewModel() {
    private val _onlyFree = MutableStateFlow(false)
    val onlyFree = _onlyFree.asStateFlow()

    fun clickOnCheckBox(){
        viewModelScope.launch {
            _onlyFree.emit(!_onlyFree.value)
        }
    }

}