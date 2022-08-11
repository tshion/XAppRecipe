package com.github.tshion.xapprecipe_core.repositories

import java.io.File

/**
 * PDF 関連操作の定義
 */
public interface PdfRepository {

    /**
     * PDF ファイル取得
     *
     * @param path ファイルパス
     */
    public suspend fun load(path: String): File?

    /**
     * PDF ファイル更新
     *
     * @param path ファイルパス
     * @param url URL
     */
    public suspend fun update(
        path: String,
        url: String,
    )
}
