package work.shion.ktrecipe.models.jewelsavior.entities

/**
 * 台詞
 * @param charaId キャラクターID
 * @param charaName キャラクター名
 * @param text 台詞
 * @param typeNote 台詞の使用シーン
 */
data class SpeechEntity(
        val charaId: String,
        val charaName: String,
        val text: String,
        val typeNote: String
)
