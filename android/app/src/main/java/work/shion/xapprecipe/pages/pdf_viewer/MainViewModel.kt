package work.shion.xapprecipe.pages.pdf_viewer

import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.os.ParcelFileDescriptor.MODE_READ_ONLY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                withContext(Dispatchers.IO) {
                    showPdfUseCase.load(
                        cacheName = "android_securecoding.pdf",
                        url = "https://www.jssec.org/dl/android_securecoding.pdf",
                    ).let {
                        PdfRenderer(ParcelFileDescriptor.open(it, MODE_READ_ONLY))
                    }.also {
                        viewer.get()?.reflectPdf(it)
                    }
                }
            } catch (ex: Throwable) {
                val a = 0
                val b = a
            }
        }
    }
}
