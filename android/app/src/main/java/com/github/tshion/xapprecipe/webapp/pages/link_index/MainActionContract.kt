package com.github.tshion.xapprecipe.webapp.pages.link_index

import com.github.tshion.xapprecipe.webapp.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract, MainItemActionContract {

    /**
     * データ読み込み
     */
    fun load()

    /**
     * データ登録
     */
    fun register(link: String?)

    /**
     * データ削除
     */
    fun remove()
}
