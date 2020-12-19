package work.shion.ktrecipe.pages.image_detail.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * 画像詳細画面の表示データ管理
 */
class MainViewModel : ViewModel() {
    val imageUrl = ObservableField<String>()
}
