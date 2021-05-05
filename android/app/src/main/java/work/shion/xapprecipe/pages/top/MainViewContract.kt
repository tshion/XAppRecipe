package work.shion.xapprecipe.pages.top

/**
 * 表示の定義
 */
interface MainViewContract {

    /**
     * ライセンス表記へ遷移
     */
    fun goLicense()

    /**
     * ログインへ遷移
     */
    fun goLogin()

    /**
     * トップへ遷移
     */
    fun goTop()

    /**
     * 外部ブラウザの起動
     */
    fun launchBrowser(uri: String)

    /**
     * メーラーの起動
     */
    fun launchMailer()

    /**
     * OS 設定の起動
     */
    fun launchOsSettings()

    /**
     * ログアウト確認の表示
     */
    fun showLogoutConfirm()

    /**
     * ログアウト完了の表示
     */
    fun showLogoutFinish()
}
