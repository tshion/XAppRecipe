package work.shion.xapprecipe_data.repositories

import work.shion.xapprecipe_core.repositories.PdfRepositoryContract
import work.shion.xapprecipe_data.file.CacheFileContract
import work.shion.xapprecipe_data.apiWeb.Api as ApiWeb

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
