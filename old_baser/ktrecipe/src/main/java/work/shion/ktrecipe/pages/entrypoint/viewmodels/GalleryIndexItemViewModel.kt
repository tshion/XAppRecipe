package work.shion.ktrecipe.pages.entrypoint.viewmodels

import androidx.databinding.ObservableField

/**
 * 画像一覧項目の表示データ管理
 */
class GalleryIndexItemViewModel {
    val cardUrl = ObservableField<String>()
    val iconUrl = ObservableField<String>()
    val title = ObservableField<String>()
}
