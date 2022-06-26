package com.github.tshion.xapprecipe.webapp_core.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.github.tshion.xapprecipe.webapp_core.entities.LoginEntity

class LoginValidatorTest {

    @Test
    fun empty() {
        listOf(
            LoginEntity(id = null, pw = null),
            LoginEntity(id = null, pw = ""),
            LoginEntity(id = "", pw = null),
            LoginEntity(id = "", pw = ""),
        ).map { LoginValidator.preCheck(it) }.forEach {
            assertThat(it?.idErrors).isNotEmpty()
            assertThat(it?.idErrors?.firstOrNull())
                .isInstanceOf(NullPointerException::class.java)

            assertThat(it?.pwErrors).isNotEmpty()
            assertThat(it?.pwErrors?.firstOrNull())
                .isInstanceOf(NullPointerException::class.java)
        }
    }

    @Test
    fun lackId() {
        val base = LoginEntity(id = null, pw = "12345678")
        listOf(
            base.copy(id = "1"),
            base.copy(id = "12"),
        ).map { LoginValidator.preCheck(it) }.forEach {
            assertThat(it?.idErrors).isNotEmpty()
            assertThat(it?.idErrors?.firstOrNull())
                .isInstanceOf(IllegalArgumentException::class.java)

            assertThat(it?.pwErrors).isNull()
        }
    }

    @Test
    fun lackPw() {
        val base = LoginEntity(id = "123", pw = null)
        listOf(
            base.copy(pw = "1"),
            base.copy(pw = "12"),
            base.copy(pw = "123"),
            base.copy(pw = "1234"),
            base.copy(pw = "12345"),
            base.copy(pw = "123456"),
            base.copy(pw = "1234567"),
        ).map { LoginValidator.preCheck(it) }.forEach {
            assertThat(it?.idErrors).isNull()

            assertThat(it?.pwErrors).isNotEmpty()
            assertThat(it?.pwErrors?.firstOrNull())
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Test
    fun success() {
        LoginEntity(id = "123", pw = "12345678")
            .let { LoginValidator.preCheck(it) }
            .also { assertThat(it).isNull() }
    }
}
