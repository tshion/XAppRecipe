package com.github.tshion.xapprecipe.webapp.pages.launcher

import com.github.tshion.xapprecipe.webapp.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract {

    /**
     * 表示ページの判定
     */
    fun judgePage()
}
