package com.example.parking.data.auth

import org.koin.dsl.module

val authModule = module {
    single<Authentication> {
        Authentication(
            carsApi = get()
        )
    }

}