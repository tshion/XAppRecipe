package work.shion.ktrecipe.pages.entrypoint.contracts

/**
 * 画像一覧項目の表示定義
 */
interface GalleryIndexItemViewContract {

    /**
     * 画像詳細画面に遷移
     * @param 詳細画像のURL
     */
    fun goDetail(url: String?)
}
