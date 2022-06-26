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
     * ローディング表示状態の反映
     */
    fun reflectLoading(shouldShow: Boolean)

    /**
     * トップへ遷移する
     */
    fun goTop()
}
