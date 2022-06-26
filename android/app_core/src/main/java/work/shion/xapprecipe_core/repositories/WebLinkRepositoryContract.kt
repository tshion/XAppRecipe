package work.shion.xapprecipe_core.repositories

import work.shion.xapprecipe_core.entities.WebLinkEntity

/**
 * WEB リンク関連のデータ入出力
 */
interface WebLinkRepositoryContract {

    /**
     * WEB リンク情報の追加
     */
    suspend fun append(path: String)

    /**
     * WEB リンク情報の一覧取得
     */
    suspend fun load(): List<WebLinkEntity>

    /**
     * WEB リンク情報の削除
     */
    suspend fun remove(id: String)

    /**
     * WEB リンク情報の更新
     */
    suspend fun update(target: WebLinkEntity)
}
