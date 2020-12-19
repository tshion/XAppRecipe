package work.shion.ktrecipe.models.jewelsavior.entities

import com.google.gson.annotations.SerializedName

/**
 * 台詞取得のレスポンス
 * @param charaId キャラクターID
 * @param charaName キャラクター名
 * @param text 台詞
 * @param typeNote 台詞の使用シーン
 */
internal data class GetSpeechItemResponse(
        @SerializedName("chara_id") val charaId: String,
        @SerializedName("chara_name") val charaName: String,
        @SerializedName("text") val text: String,
        @SerializedName("type_note") val typeNote: String
)
