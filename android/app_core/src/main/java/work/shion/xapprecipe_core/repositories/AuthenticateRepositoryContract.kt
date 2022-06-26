package work.shion.xapprecipe_core.repositories

import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException

/**
 * 認証関連のデータ入出力
 */
interface AuthenticateRepositoryContract {

    /**
     * 認証済みかどうか
     */
    suspend fun isAuthenticated(): Boolean

    /**
     * ログイン
     * @throws LoginException ログイン失敗
     */
    suspend fun login(data: LoginEntity)

    /**
     * ログアウト
     */
    suspend fun logout()
}
