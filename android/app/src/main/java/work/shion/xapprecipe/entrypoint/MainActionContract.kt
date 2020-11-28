package work.shion.xapprecipe.entrypoint

import work.shion.xapprecipe.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract {

    /**
     * 表示ページの判定
     */
    fun judgePage()
}
