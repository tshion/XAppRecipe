package work.shion.xapprecipe.pages.media_list

import android.net.Uri

/**
 * 表示の定義
 */
interface MainViewContract {

    /**
     * メディア再生アプリの起動
     */
    fun launchMediaApp(contentUri: Uri)

    /**
     * ローディング状態の反映
     */
    fun reflectLoading(shouldShow: Boolean)

    /**
     * リスト表示への反映
     */
    fun reflectList(data: List<MediaViewData>)
}
