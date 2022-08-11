package com.github.tshion.xapprecipe_data.storage

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * キャッシュ用ストレージの操作実装
 */
internal class CacheStorageDefault : CacheStorage {

    /**
     * 読み込み
     *
     * @param path ファイルパス
     */
    override suspend fun read(path: String): File? {
        return File(path).takeIf { it.exists() && it.isFile }
    }

    /**
     * 書き込み
     *
     * @param path ファイルパス
     * @param stream 書き込み内容のストリーム
     */
    override suspend fun write(
        path: String,
        stream: InputStream,
    ) {
        val outputStream = read(path)?.let { FileOutputStream(it, false) }
            ?: return
        stream.use { it.copyTo(outputStream) }
    }
}
