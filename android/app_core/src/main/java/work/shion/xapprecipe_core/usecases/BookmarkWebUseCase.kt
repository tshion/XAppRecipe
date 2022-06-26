package com.github.tshion.xapprecipe.webapp_core.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity
import com.github.tshion.xapprecipe.webapp_core.repositories.WebLinkRepositoryContract
import com.github.tshion.xapprecipe.webapp_core.validators.WebUriValidator

/**
 * WEB ページのブックマーク方法
 */
class BookmarkWebUseCase(
    private val webLinkRepository: WebLinkRepositoryContract,
) : BookmarkWebUseCaseContract {

    /**
     * ブックマークのデータストリーム
     */
    private val _bookmarkStream = MutableStateFlow<List<WebLinkEntity>>(emptyList())
    override val bookmarkStream: StateFlow<List<WebLinkEntity>> = _bookmarkStream


    /**
     * ブックマークの追加
     * @throws IllegalArgumentException path に不備がある
     */
    override suspend fun append(path: String) {
        if (!WebUriValidator.isValid(path)) {
            throw IllegalArgumentException()
        }

        val candidate = webLinkRepository.load()
            .singleOrNull { it.webPath == path }
        if (candidate != null) {
            webLinkRepository.update(candidate)
        } else {
            webLinkRepository.append(path)
        }
        refreshBookmarkStream()
    }

    /**
     * ブックマークのデータストリーム更新
     */
    override suspend fun refreshBookmarkStream() {
        _bookmarkStream.value = webLinkRepository.load()
    }

    /**
     * ブックマークの削除
     * @throws IllegalArgumentException 削除対象情報がない
     */
    override suspend fun remove(target: WebLinkEntity) {
        if (target.id.isBlank()) {
            throw IllegalArgumentException()
        }

        webLinkRepository.remove(target.id)
        refreshBookmarkStream()
    }
}
