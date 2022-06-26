package com.github.tshion.xapprecipe.webapp_core.usecases

import kotlinx.coroutines.flow.StateFlow
import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

/**
 * WEB ページのブックマーク方法の定義
 */
interface BookmarkWebUseCaseContract {

    /**
     * ブックマークのデータストリーム
     */
    val bookmarkStream: StateFlow<List<WebLinkEntity>>


    /**
     * ブックマークの追加
     * @throws IllegalArgumentException path に不備がある
     */
    suspend fun append(path: String)

    /**
     * ブックマークのデータストリーム更新
     */
    suspend fun refreshBookmarkStream()

    /**
     * ブックマークの削除
     * @throws IllegalArgumentException 削除対象情報がない
     */
    suspend fun remove(target: WebLinkEntity)
}
