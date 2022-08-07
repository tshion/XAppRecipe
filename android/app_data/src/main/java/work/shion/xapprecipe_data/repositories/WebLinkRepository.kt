package work.shion.xapprecipe_data.repositories

import com.github.tshion.xapprecipe_data.web.WebAccessor
import work.shion.xapprecipe_core.entities.WebLinkEntity
import work.shion.xapprecipe_core.repositories.WebLinkRepositoryContract
import work.shion.xapprecipe_data.inmemory.LinkMemoryContract
import java.time.LocalDateTime
import java.util.*

class WebLinkRepository(
    private val api: WebAccessor,
    private val linkMemory: LinkMemoryContract,
) : WebLinkRepositoryContract {

    companion object {
        @JvmStatic
        val regexDescription = """meta property="og:description" content="(.*)"""".toRegex()

        @JvmStatic
        private val regexImage = """meta property="og:image" content="(.*)"""".toRegex()

        @JvmStatic
        private val regexTitle = """meta property="og:title" content="(.*)"""".toRegex()
    }


    /**
     * WEB リンク情報の追加
     */
    override suspend fun append(path: String) {
        val html = api.getHtmlString(path)
        WebLinkEntity(
            appendDate = LocalDateTime.now(),
            description = html?.let { regexDescription.find(it) }?.destructured?.component1(),
            id = UUID.randomUUID().toString(),
            imagePath = html?.let { regexImage.find(it) }?.destructured?.component1(),
            title = html?.let { regexTitle.find(it) }?.destructured?.component1(),
            webPath = path,
        ).also { linkMemory.upsert(it) }
    }

    /**
     * WEB リンク情報の一覧取得
     */
    override suspend fun load() = linkMemory.load()

    /**
     * WEB リンク情報の削除
     */
    override suspend fun remove(id: String) {
        linkMemory.remove(id)
    }

    /**
     * WEB リンク情報の更新
     */
    override suspend fun update(target: WebLinkEntity) {
        target.copy(
            appendDate = LocalDateTime.now()
        ).also { linkMemory.upsert(it) }
    }
}
