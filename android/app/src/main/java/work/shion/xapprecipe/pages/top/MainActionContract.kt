package work.shion.xapprecipe.pages.top

import work.shion.xapprecipe.contracts.CancelableActionContract
import work.shion.xapprecipe.organisms.menu_for_top.MenuForTopItemType

/**
 * 挙動の定義
 */
interface MainActionContract : CancelableActionContract {

    /**
     * ログアウトの実行
     */
    fun doLogout()

    /**
     * メニュー項目の読み込み
     */
    fun loadMenu()

    /**
     * メニュータップ時
     */
    fun onTapMenu(type: MenuForTopItemType)
}
