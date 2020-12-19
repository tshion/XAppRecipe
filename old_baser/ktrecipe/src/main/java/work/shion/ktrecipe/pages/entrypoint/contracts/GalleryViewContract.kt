package work.shion.ktrecipe.pages.entrypoint.contracts

/**
 * 画像一覧の表示定義
 */
interface GalleryViewContract : GalleryIndexItemViewContract {
    fun hideProgress()
    fun showProgress()
}
