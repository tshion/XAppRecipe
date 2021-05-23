package work.shion.xapprecipe.pages.pdf_viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.shion.xapprecipe_core.usecases.ShowPdfUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val showPdfUseCase: ShowPdfUseCaseContract,
    private val viewer: WeakReference<MainViewContract>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            showPdfUseCase = showPdfUseCase,
            viewer = viewer,
        ).let { it as T }
    }
}
