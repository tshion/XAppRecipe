package work.shion.ktrecipe.pages.entrypoint.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * 画像一覧項目の表示データ管理
 */
class GalleryIndexItemViewModel : ViewModel() {
    val cardUrl = ObservableField<String>()
    val iconUrl = ObservableField<String>()
    val title = ObservableField<String>()
}
