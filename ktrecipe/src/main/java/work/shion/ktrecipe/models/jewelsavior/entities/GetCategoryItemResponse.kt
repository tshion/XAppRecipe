package work.shion.ktrecipe.models.jewelsavior.entities

import com.google.gson.annotations.SerializedName

/**
 * カテゴリー情報取得のレスポンス
 *
 * @param charaId キャラクターID
 * @param charaName キャラクター名
 * @param charaNameRead キャラクター名の読み
 * @param imageId キャラクター画像のID
 */
internal data class GetCategoryItemResponse(
        @SerializedName("chara_id") val charaId: String,
        @SerializedName("chara_name") val charaName: String,
        @SerializedName("chara_name_read") val charaNameRead: String,
        @SerializedName("image_id") val imageId: String
)
