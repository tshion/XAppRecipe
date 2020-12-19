package work.shion.ktrecipe.models.jewelsavior.entities

/**
 * カテゴリー情報
 * @param cardUrl キャラクターカード画像URL
 * @param categoryIndex カテゴリーのインデックス
 * @param charaId キャラクターID
 * @param charaName キャラクター名
 * @param charaNameRead キャラクター名の読み
 * @param iconUrl キャラクターアイコン画像URL
 */
data class CategoryEntity(
        val cardUrl: String?,
        val categoryIndex: Int,
        val charaId: String,
        val charaName: String,
        val charaNameRead: String,
        val iconUrl: String?
)
