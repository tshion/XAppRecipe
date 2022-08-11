package com.github.tshion.xapprecipe_data.repositories

import com.github.tshion.xapprecipe_core.repositories.PdfRepository
import com.github.tshion.xapprecipe_data.storage.CacheStorage
import com.github.tshion.xapprecipe_data.web.WebAccessor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * PDF 関連操作
 */
internal class PdfRepositoryDefault(
    private val cacheStorage: CacheStorage,
    private val webAccessor: WebAccessor,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO,
) : PdfRepository {

    /**
     * PDF ファイル取得
     *
     * @param path ファイルパス
     */
    override suspend fun load(path: String): File? {
        return cacheStorage.read(path)
    }

    /**
     * PDF ファイル更新
     *
     * @param path ファイルパス
     * @param url URL
     */
    override suspend fun update(
        path: String,
        url: String,
    ) {
        withContext(dispatcherIo) {
            val response = webAccessor.get(url)
            val stream = response.body?.byteStream() ?: return@withContext null
            cacheStorage.write(path, stream)
        }
    }
}
