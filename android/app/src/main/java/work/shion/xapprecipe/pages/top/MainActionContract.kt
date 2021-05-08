package work.shion.xapprecipe.pages.top

import work.shion.xapprecipe.organisms.menu_for_top.MenuForTopItemType

/**
 * 挙動の定義
 */
interface MainActionContract {

    /**
     * ログアウトの実行
     */
    fun doLogout()

    /**
     * メニュータップ時
     */
    fun onTapMenu(type: MenuForTopItemType)
}
