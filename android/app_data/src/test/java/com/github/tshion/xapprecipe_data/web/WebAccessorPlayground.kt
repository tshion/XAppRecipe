package com.github.tshion.xapprecipe_data.web

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Ignore
import org.junit.Test

/**
 * WEB 通信のお試し環境
 */
@Ignore("お試し環境のため")
internal class WebAccessorPlayground {

    private val accessor = WebAccessor(OkHttpClient())
    private val regexDescription = """meta property="og:description" content="(.*)"""".toRegex()
    private val regexImage = """meta property="og:image" content="(.*)"""".toRegex()
    private val regexTitle = """meta property="og:title" content="(.*)"""".toRegex()


    @Test
    fun downloadFile_IPASec() {
        val response = runBlocking {
            accessor.get("https://www.jssec.org/dl/android_securecoding.pdf")
        }

        assertThat(response.body?.contentType()?.type).isEqualTo("application")
        assertThat(response.body?.contentType()?.subtype).isEqualTo("pdf")
    }

    @Test
    fun getHtml_GitHub() {
        val (description: String?, image: String?, title: String?) = runBlocking {
            val html = accessor.getHtmlString("https://github.com/tshion")
            Triple(
                html?.let { regexDescription.find(it) }?.destructured?.component1(),
                html?.let { regexImage.find(it) }?.destructured?.component1(),
                html?.let { regexTitle.find(it) }?.destructured?.component1()
            )
        }

        assertThat(description.isNullOrBlank()).isFalse()
        assertThat(image.isNullOrBlank()).isFalse()
        assertThat(title.isNullOrBlank()).isFalse()
    }

    @Test
    fun getHtml_MkLog() {
        val (description: String?, image: String?, title: String?) = runBlocking {
            val html = accessor.getHtmlString("https://mokumokulog.netlify.app/")
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
