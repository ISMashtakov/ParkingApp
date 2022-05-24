package com.example.parking.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.toDate() =  Date.from(this.atZone(ZoneId.systemDefault()).toInstant());

