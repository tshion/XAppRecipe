package work.shion.ktrecipe.pages.entrypoint.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

/**
 * 画像一覧の表示データ管理
 */
class GalleryViewModel : ViewModel() {
    /** 表示データがあるかどうか */
    val hasResult = ObservableBoolean()

    /** プログレスを表示するかどうか */
    val isShowProgress = ObservableBoolean()
}
