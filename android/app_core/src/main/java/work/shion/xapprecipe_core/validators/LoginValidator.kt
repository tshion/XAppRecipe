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
            target.id.count() < 3 -> IllegalArgumentException()
            else -> null
        }

        val pwError = when {
            target.pw.isNullOrBlank() -> NullPointerException()
            target.pw.count() < 8 -> IllegalArgumentException()
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
