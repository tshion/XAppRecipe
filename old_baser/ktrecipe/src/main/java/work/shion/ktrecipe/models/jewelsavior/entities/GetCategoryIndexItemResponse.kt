package work.shion.ktrecipe.models.jewelsavior.entities

import com.google.gson.annotations.SerializedName

/**
 * カテゴリー一覧情報取得のレスポンス
 * @param index カテゴリーのインデックス
 * @param title カテゴリー名
 */
internal data class GetCategoryIndexItemResponse(
        @SerializedName("index") val index: Int,
        @SerializedName("title") val title: String
)
