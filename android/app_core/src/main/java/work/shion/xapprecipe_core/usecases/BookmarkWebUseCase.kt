package work.shion.xapprecipe_core.usecases

import work.shion.xapprecipe_core.entities.WebLinkEntity
import work.shion.xapprecipe_core.repositories.WebLinkRepositoryContract
import work.shion.xapprecipe_core.validators.WebUriValidator

/**
 * WEB ページのブックマーク方法
 */
class BookmarkWebUseCase(
    private val webLinkRepository: WebLinkRepositoryContract,
) : BookmarkWebUseCaseContract {

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
    }

    /**
     * ブックマークの読み込み
     */
    override suspend fun load() = webLinkRepository.load()

    /**
     * ブックマークの削除
     * @throws IllegalArgumentException 削除対象情報がない
     */
    override suspend fun remove(target: WebLinkEntity) {
        if (target.id.isBlank()) {
            throw IllegalArgumentException()
        }

        webLinkRepository.remove(target.id)
    }
}
