package work.shion.xapprecipe_data.repositories

import kotlinx.coroutines.delay
import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException
import work.shion.xapprecipe_core.repositories.AuthenticateRepositoryContract
import work.shion.xapprecipe_data.inmemory.TokenMemoryContract
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
