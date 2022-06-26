package com.github.tshion.xapprecipe.webapp_core.usecases

import com.github.tshion.xapprecipe.webapp_core.entities.LoginEntity
import com.github.tshion.xapprecipe.webapp_core.errors.LoginException
import com.github.tshion.xapprecipe.webapp_core.repositories.AuthenticateRepositoryContract
import com.github.tshion.xapprecipe.webapp_core.validators.LoginValidator

class CertifyAccountUseCase(
    private val authenticateRepository: AuthenticateRepositoryContract,
) : CertifyAccountUseCaseContract {

    /**
     * 認証済みかどうか
     */
    override suspend fun isAuthenticated() = authenticateRepository.isAuthenticated()

    /**
     * ログイン
     * @throws LoginException ログイン失敗
     */
    override suspend fun login(data: LoginEntity) {
        LoginValidator.preCheck(data)?.also {
            throw it
        }

        authenticateRepository.login(data)
    }

    /**
     * ログアウト
     */
    override suspend fun logout() {
        authenticateRepository.logout()
    }
}
