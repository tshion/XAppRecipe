package work.shion.xapprecipe.pages.pdf_viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import work.shion.xapprecipe_core.usecases.ShowPdfUseCaseContract
import java.lang.ref.WeakReference

class MainViewModel(
    private val showPdfUseCase: ShowPdfUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModel(), MainActionContract {

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    override fun loadPdf() {
        viewModelScope.launch {
            try {
                showPdfUseCase.load(
                    cacheName = "android_securecoding.pdf",
                    url = "https://www.jssec.org/dl/android_securecoding.pdf",
                )?.also {
                    viewer.get()?.reflectPdf(it)
                }
            } catch (ex: Throwable) {
                val a = 0
                val b = a
            }
        }
    }
}
