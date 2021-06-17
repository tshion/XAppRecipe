package work.shion.xapprecipe_data.apiConpass.v1

import com.squareup.moshi.JsonClass

/**
 * グループ
 * @param id グループID
 * @param title グループタイトル
 * @param url グループのconnpass.com 上のURL
 */
@JsonClass(generateAdapter = true)
data class Series(
    val id: Int?,
    val title: String?,
    val url: String?,
)
