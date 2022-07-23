package com.github.tshion.xapprecipe_data.api_xapp_v1

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutToDoRequest(
    val is_finish: Boolean,
    val title: String,
)
