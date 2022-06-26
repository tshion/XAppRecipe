package com.github.tshion.xapprecipe.webapp.organisms.menu_for_top

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.tshion.xapprecipe.webapp.R

enum class MenuForTopItemType(
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
) {
    CONTACT(
        R.drawable.icon_mail,
        R.string.organisms_menu_for_top_item_contact,
    ),
    LICENSE(
        R.drawable.icon_settings,
        R.string.organisms_menu_for_top_item_license,
    ),
    LOGIN(
        R.drawable.icon_login,
        R.string.organisms_menu_for_top_item_login,
    ),
    LOGOUT(
        R.drawable.icon_logout,
        R.string.organisms_menu_for_top_item_logout,
    ),
    SETTINGS(
        R.drawable.icon_settings,
        R.string.organisms_menu_for_top_item_settings,
    ),
    TWITTER(
        R.drawable.icon_link,
        R.string.organisms_menu_for_top_item_twitter,
    ),
    YOUTUBE(
        R.drawable.icon_link,
        R.string.organisms_menu_for_top_item_youtube,
    ),
    ;
}
