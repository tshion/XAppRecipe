package work.shion.ktrecipe.pages.image_detail.presenters

import work.shion.ktrecipe.pages.image_detail.contracts.MainPresenterContract
import work.shion.ktrecipe.pages.image_detail.contracts.MainViewContract
import java.lang.ref.WeakReference

/**
 * 画像詳細画面の挙動実装
 */
class MainPresenter(
        private val viewer: WeakReference<MainViewContract>
) : MainPresenterContract {

    /**
     * 前の画面に戻る
     */
    override fun backHistory() {
        viewer.get()?.close()
    }
}
