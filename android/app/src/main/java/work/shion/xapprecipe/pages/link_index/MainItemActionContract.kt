package work.shion.xapprecipe.pages.link_index

import work.shion.xapprecipe_core.entities.WebLinkEntity

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
