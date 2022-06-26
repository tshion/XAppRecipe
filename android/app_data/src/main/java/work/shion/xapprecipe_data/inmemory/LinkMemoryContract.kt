package com.github.tshion.xapprecipe.webapp_data.inmemory

import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

/**
 * リンク情報の保存定義(インメモリ)
 */
interface LinkMemoryContract {

    fun load(): List<WebLinkEntity>

    fun remove(id: String)

    fun upsert(target: WebLinkEntity)
}
