package work.shion.xapprecipe_core.usecases

import kotlinx.coroutines.flow.StateFlow
import work.shion.xapprecipe_core.entities.WebLinkEntity

/**
 * WEB ページのブックマーク方法の定義
 */
interface BookmarkWebUseCaseContract {

    /**
     * ブックマークの追加
     * @throws IllegalArgumentException path に不備がある
     */
    suspend fun append(path: String)

    suspend fun linkStream(): StateFlow<List<WebLinkEntity>>

    /**
     * ブックマークの読み込み
     */
    suspend fun load(): List<WebLinkEntity>

    /**
     * ブックマークの削除
     * @throws IllegalArgumentException 削除対象情報がない
     */
    suspend fun remove(target: WebLinkEntity)
}
