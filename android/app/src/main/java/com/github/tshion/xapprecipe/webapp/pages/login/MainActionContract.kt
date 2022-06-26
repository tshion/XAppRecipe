package com.github.tshion.xapprecipe.webapp.pages.login

import com.github.tshion.xapprecipe.webapp.contracts.CancelableActionContract

/**
 * 挙動の定義
 */
interface MainActionContract : CancelableActionContract {

    /**
     * ログインの実行
     */
    fun doLogin(
        id: String?,
        pw: String?,
    )

    /**
     * スキップの実行
     */
    fun doSkip()
}
