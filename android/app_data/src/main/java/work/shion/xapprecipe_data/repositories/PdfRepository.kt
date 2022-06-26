package com.github.tshion.xapprecipe.webapp_data.repositories

import com.github.tshion.xapprecipe.webapp_core.repositories.PdfRepositoryContract
import com.github.tshion.xapprecipe.webapp_data.file.CacheFileContract
import com.github.tshion.xapprecipe.webapp_data.apiWeb.Api as ApiWeb

class PdfRepository(
    private val apiWeb: ApiWeb,
    private val cacheFile: CacheFileContract,
) : PdfRepositoryContract {

    override suspend fun fetch(
        name: String,
        url: String,
    ) = apiWeb.downloadFile(url).body
        ?.byteStream()
        ?.let {
            cacheFile.save(name, it)
            cacheFile.load(name)
        }

    override suspend fun load(name: String) = cacheFile.load(name)
}
