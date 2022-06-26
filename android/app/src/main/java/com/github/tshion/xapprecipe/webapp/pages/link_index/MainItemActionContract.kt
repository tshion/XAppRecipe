package com.github.tshion.xapprecipe.webapp.pages.link_index

import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

interface MainItemActionContract {

    /**
     * 詳細表示要求
     */
    fun callDetail(target: WebLinkEntity)

    /**
     * リスト表示状態の変更要求
     */
    fun changeListShowState(shouldShow: Boolean)
}
