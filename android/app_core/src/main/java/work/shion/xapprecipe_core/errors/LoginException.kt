package work.shion.xapprecipe_core.errors

/**
 * ログイン失敗
 */
class LoginException(
    val idErrors: List<Throwable>?,
    val pwErrors: List<Throwable>?,
) : RuntimeException()
