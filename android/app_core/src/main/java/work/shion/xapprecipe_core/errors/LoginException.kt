package com.github.tshion.xapprecipe.webapp_core.errors

/**
 * ログイン失敗
 */
class LoginException(
    val idErrors: List<Throwable>?,
    val pwErrors: List<Throwable>?,
) : RuntimeException()
