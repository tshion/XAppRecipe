package work.shion.xapprecipe.pages.link_index

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.shion.xapprecipe_core.usecases.BookmarkWebUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val bookmarkWebUseCase: BookmarkWebUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            bookmarkWebUseCase = bookmarkWebUseCase,
            viewer = viewer,
        ).let { it as T }
    }
}
