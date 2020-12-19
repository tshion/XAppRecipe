package work.shion.ktrecipe.pages.entrypoint.contracts

/**
 * 画像項目の挙動定義
 */
interface GalleryPresenterContract : GalleryIndexItemPresenterContract {
    fun killTask()

    fun refreshAdapterData()
}
