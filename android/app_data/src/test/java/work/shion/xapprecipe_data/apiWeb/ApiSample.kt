package work.shion.xapprecipe_data.apiWeb

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test

class ApiSample {

    private val api = Api(OkHttpClient())
    private val regexDescription = """meta property="og:description" content="(.*)"""".toRegex()
    private val regexImage = """meta property="og:image" content="(.*)"""".toRegex()
    private val regexTitle = """meta property="og:title" content="(.*)"""".toRegex()


    @Test
    fun getHtml_MkLog() {
        val (description: String?, image: String?, title: String?) = runBlocking {
            val html = api.getHtml("https://mokumokulog.netlify.app/")
            Triple(
                html?.let { regexDescription.find(it) }?.destructured?.component1(),
                html?.let { regexImage.find(it) }?.destructured?.component1(),
                html?.let { regexTitle.find(it) }?.destructured?.component1()
            )
        }

        assertThat(description.isNullOrBlank()).isFalse()
        assertThat(image.isNullOrBlank()).isTrue()
        assertThat(title.isNullOrBlank()).isFalse()
    }
}
