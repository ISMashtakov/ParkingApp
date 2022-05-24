package com.example.parking

import android.app.Application
import android.util.JsonWriter
import com.example.parking.data.auth.Authentication
import com.example.parking.data.dataModule
import com.example.parking.general.generalModule
import com.example.parking.ui.admin.adminModule
import com.example.parking.ui.login.loginModule
import com.example.parking.ui.user.userModule
import com.google.gson.*
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
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ParkingApp : Application() {
    lateinit var retrofit: Retrofit
    private val authentication: Authentication by inject()

    private fun setUpRetrofit() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        Credentials.basic(authentication.login, authentication.password)
                    )
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .build()



        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): Date {
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S", Locale.US)
                    format.timeZone = TimeZone.getTimeZone("GMT")
                    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
                    format2.timeZone = TimeZone.getTimeZone("GMT")
                    val date: String = json?.asString ?: throw JsonSyntaxException("Null instead date")
                    try {
                        return format.parse(date)!!
                    }
                    catch (ex: Exception){
                        return format2.parse(date)!!
                    }
                }
            })
            .registerTypeAdapter(Date::class.java, object : JsonSerializer<Date> {
                override fun serialize(
                    src: Date?,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ): JsonElement {
                    if (src == null){
                        throw JsonSyntaxException("Can't convert date = null to json")
                    }
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'", Locale.US)
                    format.timeZone = TimeZone.getTimeZone("GMT")

                    return JsonPrimitive(format.format(src))
                }

            })
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ParkingApp)
            modules(
                listOf(
                    loginModule,
                    userModule,
                    adminModule,
                    dataModule,
                    generalModule
                )
            )
        }
    }

    override fun onCreate() {
        super.onCreate()

        setUpRetrofit()
        setUpKoin()
    }
}