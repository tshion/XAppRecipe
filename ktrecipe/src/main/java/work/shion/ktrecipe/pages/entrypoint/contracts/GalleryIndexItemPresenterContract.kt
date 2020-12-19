package work.shion.ktrecipe.pages.entrypoint.contracts

/**
 * 画像一覧項目の挙動定義
 */
interface GalleryIndexItemPresenterContract {

    /**
     * 画像詳細画面の呼び出し
     * @param 詳細画像のURL
     */
    fun callDetail(url: String?)
}
