package work.shion.xapprecipe_core.usecases

import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException
import work.shion.xapprecipe_core.repositories.AuthenticateRepositoryContract
import work.shion.xapprecipe_core.validators.LoginValidator

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
