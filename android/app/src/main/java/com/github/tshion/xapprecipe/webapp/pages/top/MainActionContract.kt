package com.github.tshion.xapprecipe.webapp.pages.top

import com.github.tshion.xapprecipe.webapp.contracts.CancelableActionContract
import com.github.tshion.xapprecipe.webapp.organisms.menu_for_top.MenuForTopItemType

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
