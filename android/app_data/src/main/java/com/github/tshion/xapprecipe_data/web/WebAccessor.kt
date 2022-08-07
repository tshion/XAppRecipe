package com.github.tshion.xapprecipe_data.web

import com.github.tshion.xapprecipe_data.utils.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Web アクセスする実装群
 */
// FIXME: 公開範囲をinternal に変更する(2022/08/07)
public class WebAccessor(
    private val client: OkHttpClient,
    private val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default,
) {

    /**
     * GET 通信
     *
     * @param target 取得先パス
     */
    suspend fun get(target: String): Response {
        val request = Request.Builder()
            .url(target)
            .build()
        return client.newCall(request).await()
    }

    /**
     * HTML ファイル文字列の取得
     *
     * @param target 取得先パス
     */
    suspend fun getHtmlString(target: String): String? {
        val response = get(target)
        return withContext(dispatcherDefault) {
            if (response.isSuccessful
                && response.header("Content-Type")?.contains("text/html") == true
            ) {
                response.body?.string()
            } else {
                null
            }
        }
    }
}
