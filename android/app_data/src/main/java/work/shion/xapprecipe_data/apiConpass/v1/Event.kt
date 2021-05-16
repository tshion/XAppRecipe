package work.shion.xapprecipe_data.apiConpass.v1

import java.time.LocalDateTime

/**
 * イベント
 * @param accepted 参加者数
 * @param address 開催場所
 * @param &#x60;catch&#x60; キャッチ
 * @param description 概要(HTML形式)
 * @param endedAt イベント開催日時 (ISO-8601形式)
 * @param eventId イベントID
 * @param eventType イベント参加タイプ
 * @param eventUrl connpass.com 上のURL
 * @param hashTag Twitterのハッシュタグ
 * @param lat 開催会場の緯度
 * @param limit 定員
 * @param lon 開催会場の経度
 * @param ownerDisplayName 管理者の表示名
 * @param ownerId 管理者のID
 * @param ownerNickname 管理者のニックネーム
 * @param place 開催会場
 * @param series グループ
 * @param startedAt イベント開催日時 (ISO-8601形式)
 * @param title タイトル
 * @param updatedAt 更新日時 (ISO-8601形式)
 * @param waiting 補欠者数
 */
data class Event(
    val accepted: Int?,
    val address: String?,
    val `catch`: String?,
    val description: String?,
    val endedAt: LocalDateTime?,
    val eventId: Int?,
    val eventType: String?,
    val eventUrl: String?,
    val hashTag: String?,
    val lat: Double?,
    val limit: Int?,
    val lon: Double?,
    val ownerDisplayName: String?,
    val ownerId: Int?,
    val ownerNickname: String?,
    val place: String?,
    val series: Series?,
    val startedAt: LocalDateTime?,
    val title: String?,
    val updatedAt: LocalDateTime?,
    val waiting: Int?,
)
