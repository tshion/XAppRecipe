package work.shion.xapprecipe.pages.login

import work.shion.xapprecipe.contracts.CancelableActionContract

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
