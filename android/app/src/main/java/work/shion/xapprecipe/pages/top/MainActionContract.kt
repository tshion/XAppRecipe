package work.shion.xapprecipe.pages.top

import work.shion.xapprecipe.organisms.menu_for_top.MenuForTopItemType

/**
 * 挙動の定義
 */
interface MainActionContract {

    /**
     * メニュータップ時
     */
    fun onTapMenu(type: MenuForTopItemType)
}
