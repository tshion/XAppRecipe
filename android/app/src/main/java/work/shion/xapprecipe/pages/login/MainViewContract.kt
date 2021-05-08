package work.shion.xapprecipe.pages.login

/**
 * 表示の定義
 */
interface MainViewContract {

    /**
     * エラー表示の反映
     */
    fun reflectError(
        idError: String?,
        pwError: String?,
    )

    /**
     * トップへ遷移する
     */
    fun goTop()
}
