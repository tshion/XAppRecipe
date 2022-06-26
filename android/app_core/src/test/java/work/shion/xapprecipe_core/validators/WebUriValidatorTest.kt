package com.github.tshion.xapprecipe.webapp_core.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class WebUriValidatorTest {

    @Test
    fun failure() {
        listOf(
            null,
            "aaahttp://mokumokulog.netlify.app/",
            "aaahttps://mokumokulog.netlify.app/",
            "file://hoge",
            "htaaatp://mokumokulog.netlify.app/",
            "htaaatps://mokumokulog.netlify.app/",
            "mailto://hoge",
        ).forEach {
            assertThat(WebUriValidator.isValid(it)).isFalse()
        }
    }

    @Test
    fun success() {
        listOf(
            "http://mokumokulog.netlify.app/",
            "https://mokumokulog.netlify.app/",
        ).forEach {
            assertThat(WebUriValidator.isValid(it)).isTrue()
        }
    }
}
