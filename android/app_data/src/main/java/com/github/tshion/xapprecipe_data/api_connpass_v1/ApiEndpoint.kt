package com.github.tshion.xapprecipe_data.api_connpass_v1

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * V1 のエンドポイント
 * [connpass API リファレンス](https://connpass.com/about/api/)
 */
internal interface ApiEndpoint {

    /**
     * イベントサーチ
     *
     * @param count 取得件数 (optional, default to 10)
     * @param eventId イベントID (optional)
     * @param format レスポンス形式 (optional, default to json)
     * @param keyword キーワード (AND) (optional)
     * @param keywordOr キーワード (OR) (optional)
     * @param nickname 参加者のニックネーム (optional)
     * @param order 検索結果の表示順 (optional, default to 1)
     * @param ownerNickname 管理者のニックネーム (optional)
     * @param seriesId グループID (optional)
     * @param start 検索の開始位置 (optional, default to 1)
     * @param ym イベント開催年月 (optional)
     * @param ymd イベント開催年月日 (optional)
     */
    @GET("v1/event")
    suspend fun getEventSearch(
        @Query("count") count: Int = 10,
        @Query("event_id") eventId: Array<Int>? = null,
        @Query("format") format: String = "json",
        @Query("keyword") keyword: Array<String>? = null,
        @Query("keyword_or") keywordOr: Array<String>? = null,
        @Query("nickname") nickname: Array<String>? = null,
        @Query("order") order: Int = 1,
        @Query("owner_nickname") ownerNickname: Array<String>? = null,
        @Query("series_id") seriesId: Array<Int>? = null,
        @Query("start") start: Int = 1,
        @Query("ym") ym: Array<String>? = null,
        @Query("ymd") ymd: Array<String>? = null,
    ): GetEventSearchResponse
}
