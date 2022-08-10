package com.github.tshion.xapprecipe_data.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class LocalDateAdapter(
    private val fromFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    private val toFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
) {

    @ToJson
    fun toJson(value: LocalDate) = toFormatter.format(value)

    @FromJson
    fun fromJson(value: String) = LocalDate.parse(value, fromFormatter)
}
