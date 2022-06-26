package work.shion.xapprecipe.pages.launcher

import work.shion.xapprecipe.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract {

    /**
     * 表示ページの判定
     */
    fun judgePage()
}
