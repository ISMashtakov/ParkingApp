package com.example.parking

import android.app.Application
import android.net.wifi.hotspot2.pps.Credential
import com.example.parking.data.cars.CarsApi
import okhttp3.Credentials
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ParkingApp: Application() {
    lateinit var carsApi: CarsApi

    override fun onCreate() {
        super.onCreate()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor{ chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic("admin", "password"))
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }
}