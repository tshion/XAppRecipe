package com.github.tshion.xapprecipe.webapp.pages.media_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val application: Application,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            application = application,
            viewer = viewer,
        ).let { it as T }
    }
}
