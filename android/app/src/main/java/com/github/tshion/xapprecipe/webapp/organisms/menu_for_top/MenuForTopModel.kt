package com.github.tshion.xapprecipe.webapp.organisms.menu_for_top

internal class MenuForTopModel {

    companion object {
        private val menus = listOf(
            MenuForTopItemType.TWITTER,
            MenuForTopItemType.YOUTUBE,
            MenuForTopItemType.SETTINGS,
            MenuForTopItemType.CONTACT,
            MenuForTopItemType.LICENSE,
        )
    }


    fun loadMenuLogin() = MenuForTopItemType.LOGOUT
        .let { menus.plus(it) }

    fun loadMenuLogout() = MenuForTopItemType.LOGIN
        .let { listOf(it).plus(menus) }
}
