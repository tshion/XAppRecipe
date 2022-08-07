package work.shion.xapprecipe_data.repositories

import com.github.tshion.xapprecipe_data.web.WebAccessor
import work.shion.xapprecipe_core.repositories.PdfRepositoryContract
import work.shion.xapprecipe_data.file.CacheFileContract

class PdfRepository(
    private val apiWeb: WebAccessor,
    private val cacheFile: CacheFileContract,
) : PdfRepositoryContract {

    override suspend fun fetch(
        name: String,
        url: String,
    ) = apiWeb.get(url).body
        ?.byteStream()
        ?.let {
            cacheFile.save(name, it)
            cacheFile.load(name)
        }

    override suspend fun load(name: String) = cacheFile.load(name)
}
