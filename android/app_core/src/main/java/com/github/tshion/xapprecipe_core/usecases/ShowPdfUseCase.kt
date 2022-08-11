package com.github.tshion.xapprecipe_core.usecases

import java.io.File

/**
 * PDF 表示フローの定義
 */
public interface ShowPdfUseCase {

    /**
     * PDF の読み込み
     *
     * @param cacheName キャッシュ用のファイル名
     * @param url PDF があるURL
     */
    public suspend fun load(
        cacheName: String,
        url: String,
    ): File?
}
