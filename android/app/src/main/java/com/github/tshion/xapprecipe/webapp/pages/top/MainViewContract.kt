package com.github.tshion.xapprecipe.webapp.pages.top

/**
 * 表示の定義
 */
interface MainViewContract {

    /**
     * メニューを閉じる
     */
    fun closeMenu()

    /**
     * ライセンス表記へ遷移
     */
    fun goLicense()

    /**
     * ログインへ遷移
     */
    fun goLogin()

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
     * メニューを開く
     */
    fun openMenu()

    /**
     * ローディング状態の反映
     */
    fun reflectLoading(shouldShow: Boolean)

    /**
     * メニュー項目の反映
     */
    fun reflectMenu(isLogin: Boolean)

    /**
     * ログアウト確認の表示
     */
    fun showLogoutConfirm()

    /**
     * ログアウト完了の表示
     */
    fun showLogoutFinish()
}
