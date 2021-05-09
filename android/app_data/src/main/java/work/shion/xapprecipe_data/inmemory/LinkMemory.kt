package work.shion.xapprecipe_data.inmemory

import work.shion.xapprecipe_core.entities.WebLinkEntity

/**
 * リンク情報の保存実装(インメモリ)
 */
class LinkMemory : LinkMemoryContract {

    val store = arrayListOf<WebLinkEntity>()


    override fun load() = store.map { it.copy() }

    override fun remove(id: String) {
        store.removeAll { it.id == id }
    }

    override fun upsert(target: WebLinkEntity) {
        val index = store.indexOfFirst { it.id == target.id }
        if (0 <= index) {
            store[index] = target
        } else {
            store.add(target)
        }
    }
}
