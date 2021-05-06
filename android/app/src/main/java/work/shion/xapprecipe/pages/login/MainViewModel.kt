package work.shion.xapprecipe.pages.login

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

class MainViewModel(
    private val viewer: WeakReference<MainViewContract>
) : ViewModel(), MainActionContract {

    /**
     * ログインの実行
     */
    override fun doLogin(id: String?, pw: String?) {
        // TODO: ログイン処理
        viewer.get()?.reflectError(
            idError = "ID: $id の判定は未実装",
            pwError = "PW: $pw の判定は未実装",
        )
    }

    /**
     * スキップの実行
     */
    override fun doSkip() {
        viewer.get()?.goTop()
    }
}
