package com.github.tshion.xapprecipe.webapp.pages.link_index

import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

interface MainViewContract {

    /**
     * 通知一覧へ遷移
     */
    fun goNews()

    /**
     * リスト表示用データの反映
     */
    fun reflectList(data: List<WebLinkEntity>?)

    /**
     * リスト表示状態の反映
     */
    fun reflectListShowState(shouldShow: Boolean)

    /**
     * ローディング表示状態の反映
     */
    fun reflectLoading(shouldShow: Boolean)

    /**
     * 登録ダイアログの表示
     */
    fun showEditor()

    /**
     * リンク詳細の表示
     */
    fun showLinkDetail(data: WebLinkEntity)

    /**
     * メニューの表示
     */
    fun showMenu()
}
