package com.github.tshion.xapprecipe.webapp_core.usecases

import com.github.tshion.xapprecipe.webapp_core.entities.LoginEntity
import com.github.tshion.xapprecipe.webapp_core.errors.LoginException

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
