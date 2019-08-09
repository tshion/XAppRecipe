package work.shion.ktrecipe.pages.entrypoint.presenters

import work.shion.ktrecipe.pages.entrypoint.contracts.TutorialPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.TutorialViewContract
import java.lang.ref.WeakReference


/**
 * チュートリアルの挙動実装
 */
class TutorialPresenter(
        private val viewer: WeakReference<TutorialViewContract>
) : TutorialPresenterContract {

    /**
     * TabPage の呼び出し
     */
    override fun callTabPage() {
        viewer.get()?.apply {
            goTabPage()
        }
    }
}
