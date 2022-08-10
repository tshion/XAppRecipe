package com.github.tshion.xapprecipe_data.api_connpass_v1

import com.squareup.moshi.JsonClass

/**
 * グループ
 *
 * @param id グループID
 * @param title グループタイトル
 * @param url グループのconnpass.com 上のURL
 */
@JsonClass(generateAdapter = true)
internal data class Series(
    val id: Int?,
    val title: String?,
    val url: String?,
)
