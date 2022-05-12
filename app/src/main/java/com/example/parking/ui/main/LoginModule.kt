package com.example.parking.ui.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel<LoginViewModel>{
        LoginViewModel(
            authentication = get()
        )
    }
}