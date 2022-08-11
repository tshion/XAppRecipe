package com.github.tshion.xapprecipe_data.storage

import java.io.File
import java.io.InputStream

/**
 * キャッシュ用ストレージの操作定義
 */
internal interface CacheStorage {

    /**
     * 読み込み
     *
     * @param path ファイルパス
     */
    suspend fun read(path: String): File?

    /**
     * 書き込み
     *
     * @param path ファイルパス
     * @param stream 書き込み内容のストリーム
     */
    suspend fun write(path: String, stream: InputStream)
}
