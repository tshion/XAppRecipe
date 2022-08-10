package com.github.tshion.xapprecipe_data.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class LocalDateTimeAdapter(
    private val fromFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    private val toFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
) {

    @ToJson
    fun toJson(value: LocalDateTime) = toFormatter.format(value)

    @FromJson
    fun fromJson(value: String) = LocalDateTime.parse(value, fromFormatter)
}
