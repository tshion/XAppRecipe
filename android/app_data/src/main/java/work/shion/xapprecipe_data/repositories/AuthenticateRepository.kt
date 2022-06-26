package com.github.tshion.xapprecipe.webapp_data.repositories

import kotlinx.coroutines.delay
import com.github.tshion.xapprecipe.webapp_core.entities.LoginEntity
import com.github.tshion.xapprecipe.webapp_core.errors.LoginException
import com.github.tshion.xapprecipe.webapp_core.repositories.AuthenticateRepositoryContract
import com.github.tshion.xapprecipe.webapp_data.inmemory.TokenMemoryContract
import java.util.*

class AuthenticateRepository(
    private val tokenMemory: TokenMemoryContract,
) : AuthenticateRepositoryContract {

    /**
     * 認証済みかどうか
     */
    override suspend fun isAuthenticated() = tokenMemory.token
        .isNullOrBlank()
        .not()

    /**
     * ログイン
     * @throws LoginException ログイン失敗
     */
    override suspend fun login(data: LoginEntity) {
        delay(1500)

        if (data.id != "aaa" || data.pw != "12345678") {
            throw LoginException(
                idErrors = listOf(IllegalArgumentException()),
                pwErrors = listOf(IllegalArgumentException()),
            )
        }

        tokenMemory.token = UUID.randomUUID().toString()
    }

    /**
     * ログアウト
     */
    override suspend fun logout() {
        delay(1500)

        tokenMemory.token = null
    }
}
