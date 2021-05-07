package work.shion.xapprecipe_core.validators

import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException

object LoginValidator {

    /**
     * 事前検証(外部サービス連携前)
     */
    fun preCheck(target: LoginEntity): LoginException? {
        val idError = when {
            target.id.isNullOrBlank() -> NullPointerException()
            3 < target.id.count() -> IllegalArgumentException()
            else -> null
        }

        val pwError = when {
            target.pw.isNullOrBlank() -> NullPointerException()
            8 < target.pw.count() -> IllegalArgumentException()
            else -> null
        }

        return (idError ?: pwError)?.let {
            LoginException(
                idErrors = idError?.let { listOf(it) },
                pwErrors = pwError?.let { listOf(it) },
            )
        }
    }
}
