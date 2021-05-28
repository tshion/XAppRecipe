package work.shion.xapprecipe.pages.pdf_viewer

import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
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

    private var currentIndex = 0
    private var currentPage: PdfRenderer.Page? = null
    private var pdfRenderer: PdfRenderer? = null


    /** タスクキャンセル */
    override fun cancelTasks() {
        viewModelScope.coroutineContext.cancelChildren()
        clearPage()
    }

    override fun loadPdf() {
        viewModelScope.launch {
            try {
                val file = showPdfUseCase.load(
                    cacheName = "android_securecoding.pdf",
                    url = "https://www.jssec.org/dl/android_securecoding.pdf",
                )

                clearPage()

                pdfRenderer = withContext(Dispatchers.IO) {
                    ParcelFileDescriptor.open(
                        file,
                        ParcelFileDescriptor.MODE_READ_ONLY
                    ).let { PdfRenderer(it) }
                }

                currentPage = pdfRenderer?.openPage(currentIndex)
                currentPage?.also { viewer.get()?.reflectPdf(it) }
            } catch (ex: Throwable) {
                val a = 0
                val b = a
            }
        }
    }

    override fun loadPrev() {
        var index = currentIndex - 1
        if (index < 0) {
            index = 0
        }
        currentIndex = index

        currentPage?.close()
        currentPage = pdfRenderer?.openPage(currentIndex)
        currentPage?.also { viewer.get()?.reflectPdf(it) }
    }

    override fun loadNext() {
        var index = currentIndex + 1
        if ((pdfRenderer?.pageCount ?: 0) <= index) {
            index = (pdfRenderer?.pageCount ?: 0) - 1
        }
        currentIndex = index

        currentPage?.close()
        currentPage = pdfRenderer?.openPage(currentIndex)
        currentPage?.also { viewer.get()?.reflectPdf(it) }
    }


    private fun clearPage() {
        currentPage?.close()
        currentPage = null

        currentIndex = 0

        pdfRenderer?.close()
        pdfRenderer = null
    }
}
