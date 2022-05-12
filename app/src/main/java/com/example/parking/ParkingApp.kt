package com.example.parking

import android.app.Application
import com.example.parking.data.auth.Authentication
import com.example.parking.data.auth.authModule
import com.example.parking.data.cars.carsModule
import com.example.parking.ui.main.loginModule
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ParkingApp: Application() {
    lateinit var retrofit: Retrofit
    val authentication : Authentication by inject()
    private fun setUpRetrofit() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor{ chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        Credentials.basic(authentication.login, authentication.password)
                    )
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun setUpKoin() {
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@ParkingApp)
            modules(listOf(
                loginModule,
                carsModule,
                authModule
            ))
        }
    }

    override fun onCreate() {
        super.onCreate()

        setUpRetrofit()
        setUpKoin()
    }
}