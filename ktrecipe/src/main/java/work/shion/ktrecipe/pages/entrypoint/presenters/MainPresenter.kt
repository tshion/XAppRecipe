package work.shion.ktrecipe.pages.entrypoint.presenters

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import work.shion.ktrecipe.pages.entrypoint.contracts.MainPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.MainViewContract
import work.shion.ktrecipe.pages.entrypoint.models.MainModel
import java.lang.ref.WeakReference


/**
 * メインコンテンツの挙動実装
 */
class MainPresenter(
        private val model: MainModel,
        private val viewer: WeakReference<MainViewContract>
) : MainPresenterContract {

    /**
     * 最初に表示する画面の呼び出し
     */
    override fun callFirstView() {
        val subThread = HandlerThread("sub").apply { start() }.looper
        Handler(subThread).postDelayed({
            val random = model.getRandom()
            viewer.get()?.apply {
                Handler(Looper.getMainLooper()).post {
                    if (random < 50) {
                        goTutorialPage()
                    } else {
                        goTabPage()
                    }
                }
            }
        }, 1500)
    }
}
