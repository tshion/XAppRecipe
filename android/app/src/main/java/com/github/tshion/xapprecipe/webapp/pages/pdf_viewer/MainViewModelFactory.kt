package com.github.tshion.xapprecipe.webapp.pages.pdf_viewer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.tshion.xapprecipe.webapp_core.usecases.ShowPdfUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val application: Application,
    private val showPdfUseCase: ShowPdfUseCaseContract,
    private val viewer: WeakReference<MainViewContract>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            application = application,
            showPdfUseCase = showPdfUseCase,
            viewer = viewer,
        ).let { it as T }
    }
}
