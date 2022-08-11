package com.github.tshion.xapprecipe_core.usecases

import com.github.tshion.xapprecipe_core.repositories.PdfRepository
import java.io.File

/**
 * PDF 表示フローの標準実装
 *
 * @param cacheRoot キャッシュ配置先のルート
 * @param pdfRepository PDF 関連操作リポジトリ
 */
public class ShowPdfUseCaseDefault(
    private val cacheRoot: File,
    private val pdfRepository: PdfRepository,
) : ShowPdfUseCase {

    private fun getFilePath(name: String): String {
        return File(cacheRoot, name).path
    }

    /**
     * PDF の読み込み
     *
     * @param cacheName キャッシュ用のファイル名
     * @param url PDF があるURL
     */
    override suspend fun load(cacheName: String, url: String): File? {
        val path = getFilePath(cacheName)
        val candidate = pdfRepository.load(path)
        if (candidate != null) {
            return candidate
        }

        pdfRepository.update(path, url)
        return pdfRepository.load(path)
    }
}
