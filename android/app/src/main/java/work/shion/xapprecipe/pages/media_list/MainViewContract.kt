package work.shion.xapprecipe.pages.media_list

/**
 * 表示の定義
 */
interface MainViewContract {

    /**
     * ローディング状態の反映
     */
    fun reflectLoading(shouldShow: Boolean)

    /**
     * リスト表示への反映
     */
    fun reflectList(data: List<MediaViewData>)
}
