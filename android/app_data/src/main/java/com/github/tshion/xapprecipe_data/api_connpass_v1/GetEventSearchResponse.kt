package com.github.tshion.xapprecipe_data.api_connpass_v1

import com.squareup.moshi.JsonClass

/**
 * @param events 検索結果のイベントリスト
 * @param resultsAvailable 検索結果の総件数
 * @param resultsReturned 含まれる検索結果の件数
 * @param resultsStart 検索の開始位置
 */
@JsonClass(generateAdapter = true)
internal data class GetEventSearchResponse(
    val events: List<Event>?,
    val resultsAvailable: Int?,
    val resultsReturned: Int?,
    val resultsStart: Int?,
)
