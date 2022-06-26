package work.shion.xapprecipe_core.usecases

import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException

/**
 * アカウント認証方法の定義
 */
interface CertifyAccountUseCaseContract {

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
