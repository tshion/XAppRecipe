package com.github.tshion.xapprecipe.webapp.pages.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val viewer: WeakReference<MainViewContract>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            viewer = viewer
        ).let { it as T }
    }
}
