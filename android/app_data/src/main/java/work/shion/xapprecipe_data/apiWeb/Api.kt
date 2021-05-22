package work.shion.xapprecipe_data.apiWeb

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class Api(
    private val client: OkHttpClient,
) {

    /**
     * ファイルのダウンロード
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun downloadFile(path: String) = withContext(Dispatchers.IO) {
        Request.Builder()
            .url(path)
            .build()
            .let { client.newCall(it) }
            .execute()
    }

    /**
     * HTML の取得
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun getHtml(path: String): String? = withContext(Dispatchers.IO) {
        val response = Request.Builder()
            .url(path)
            .build()
            .let { client.newCall(it) }
            .execute()

        if (response.header("Content-Type")?.contains("text/html") == true) {
            response.body?.string()
        } else {
            null
        }
    }
}
