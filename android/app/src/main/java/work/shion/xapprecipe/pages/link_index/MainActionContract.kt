package work.shion.xapprecipe.pages.link_index

import work.shion.xapprecipe.contracts.CancelableActionContract

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
