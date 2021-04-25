package work.shion.xapprecipe

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

class FetchOgpSample {

    private val client = OkHttpClient()
    private val regexDescription = """meta property="og:description" content="(.*)"""".toRegex()
    private val regexImage = """meta property="og:image" content="(.*)"""".toRegex()
    private val regexTitle = """meta property="og:title" content="(.*)"""".toRegex()


    @Test
    fun fetchMkLog() {
        val request = Request.Builder()
            .url("https://mokumokulog.netlify.app/")
            .build()

        val (description: String?, image: String?, title: String?) = runBlocking {
            withContext(Dispatchers.IO) {
                val html = client.newCall(request)
                    .execute()
                    .body
                    ?.string()

                Triple(
                    html?.let { regexDescription.find(it) }?.destructured?.component1(),
                    html?.let { regexImage.find(it) }?.destructured?.component1(),
                    html?.let { regexTitle.find(it) }?.destructured?.component1()
                )
            }
        }

        assertThat(description.isNullOrBlank()).isFalse()
        assertThat(image.isNullOrBlank()).isTrue()
        assertThat(title.isNullOrBlank()).isFalse()
    }
}
