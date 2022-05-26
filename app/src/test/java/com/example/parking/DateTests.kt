package com.example.parking

import com.example.parking.utils.toDate
import org.junit.Test
import java.text.SimpleDateFormat

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DateTests {
    @Test
    fun localDateTimeConvertToDateCorrected() {
        val localDateTime = LocalDateTime.of(2022, 4, 20, 12, 32)
        val date = localDateTime.toDate()

        val formatString = "yyyy-MM-dd'T'HH:mm:ss.S"
        val simpleDateFormat = SimpleDateFormat(formatString)
        val simpleLocalDateFormat = DateTimeFormatter.ofPattern(formatString)
        assertEquals(
            simpleLocalDateFormat.format(localDateTime),
            simpleDateFormat.format(date),
            "localDateTime converted to date wrong"
        )
    }
}