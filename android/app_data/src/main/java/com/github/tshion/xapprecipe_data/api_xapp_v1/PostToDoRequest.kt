package com.github.tshion.xapprecipe_data.api_xapp_v1

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PostToDoRequest(
    val title: String,
)
