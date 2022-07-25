package com.github.tshion.xapprecipe_data.api_xapp_v1

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
internal data class ToDo(
    val id: String,
    val is_finish: Boolean,
    val title: String,
    val update_date: Date,
)
