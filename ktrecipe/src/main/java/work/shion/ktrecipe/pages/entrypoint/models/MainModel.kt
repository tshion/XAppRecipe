package work.shion.ktrecipe.pages.entrypoint.models

import java.util.*
import kotlin.random.Random


/**
 * メインコンテンツ用のビジネスロジック実装
 */
class MainModel {

    /**
     * 乱数の取得
     * @param seed 乱数生成時の初期値
     */
    fun getRandom(
            seed: Long = Date().time
    ) = Random(seed).nextInt(100)
}
