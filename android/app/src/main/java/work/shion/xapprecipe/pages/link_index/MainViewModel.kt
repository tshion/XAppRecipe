package work.shion.xapprecipe.pages.link_index

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import work.shion.xapprecipe_core.entities.WebLinkEntity
import work.shion.xapprecipe_core.usecases.BookmarkWebUseCaseContract
import java.lang.ref.WeakReference

class MainViewModel(
    private val bookmarkWebUseCase: BookmarkWebUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModel(), MainActionContract {

    private var selectedData: WebLinkEntity? = null


    init {
        viewModelScope.launch {
            bookmarkWebUseCase.linkStream().collect {
                viewer.get()?.reflectList(it)
            }
        }
    }


    /**
     * 詳細表示要求
     */
    override fun callDetail(target: WebLinkEntity) {
        selectedData = target
        viewer.get()?.showLinkDetail(target)
    }

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    /**
     * リスト表示状態の変更要求
     */
    override fun changeListShowState(shouldShow: Boolean) {
        viewer.get()?.reflectListShowState(shouldShow)
    }

    /**
     * データ読み込み
     */
    override fun load() {
        viewModelScope.launch {
            try {
                viewer.get()?.reflectLoading(true)
//                bookmarkWebUseCase.load()
//                    .also { viewer.get()?.reflectList(it) }
            } catch (ex: Throwable) {
                // TODO: エラー表示
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
    }

    /**
     * データ登録
     */
    override fun register(link: String?) {
        viewModelScope.launch {
            try {
                viewer.get()?.reflectLoading(true)

                link?.also { bookmarkWebUseCase.append(it) }

//                bookmarkWebUseCase.load()
//                    .also { viewer.get()?.reflectList(it) }
            } catch (ex: Throwable) {
                // TODO: エラー表示
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
    }

    /**
     * データ削除
     */
    override fun remove() {
        viewModelScope.launch {
            try {
                viewer.get()?.reflectLoading(true)

                selectedData?.also { bookmarkWebUseCase.remove(it) }
                selectedData = null

//                bookmarkWebUseCase.load()
//                    .also { viewer.get()?.reflectList(it) }
            } catch (ex: Throwable) {
                // TODO: エラー表示
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
    }
}
