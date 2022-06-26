package com.github.tshion.xapprecipe.webapp.pages.link_index

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.tshion.xapprecipe.webapp_core.usecases.BookmarkWebUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val bookmarkWebUseCase: BookmarkWebUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            bookmarkWebUseCase = bookmarkWebUseCase,
            viewer = viewer,
        ).let { it as T }
    }
}
