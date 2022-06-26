package work.shion.xapprecipe_data.inmemory

import work.shion.xapprecipe_core.entities.WebLinkEntity

/**
 * リンク情報の保存定義(インメモリ)
 */
interface LinkMemoryContract {

    fun load(): List<WebLinkEntity>

    fun remove(id: String)

    fun upsert(target: WebLinkEntity)
}
