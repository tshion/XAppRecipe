package work.shion.xapprecipe.pages.login

/**
 * 挙動の定義
 */
interface MainActionContract {

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
