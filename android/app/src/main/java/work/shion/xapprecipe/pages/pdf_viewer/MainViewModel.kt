package work.shion.xapprecipe.pages.pdf_viewer

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.os.ParcelFileDescriptor.MODE_READ_ONLY
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.util.*
import work.shion.xapprecipe_data.apiWeb.Api as ApiWeb

class MainViewModel(
    private val apiWeb: ApiWeb,
    private val appContext: WeakReference<Context>,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModel(), MainActionContract {

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    override fun loadPdf() {
        viewModelScope.launch {
            try {
                val body = apiWeb
                    .downloadFile("https://www.jssec.org/dl/android_securecoding.pdf")
                    .body

                val pdfFile = withContext(Dispatchers.IO) {
                    val directory = ContextCompat
                        .getExternalFilesDirs(appContext.get()!!, "documents")
                        .first()
                    val outputDir = File(directory, "outputPath")
                    val file = File(outputDir, UUID.randomUUID().toString() + ".pdf")
                    if (!outputDir.exists()) {
                        outputDir.mkdirs()
                    }
                    val outputStream = FileOutputStream(file, false)
                    body?.byteStream()?.use { fileOut -> fileOut.copyTo(outputStream) }
                    outputStream.close()
                    return@withContext file
                }

                val pdf = withContext(Dispatchers.IO) {
                    PdfRenderer(ParcelFileDescriptor.open(pdfFile, MODE_READ_ONLY))
                }

                viewer.get()?.reflectPdf(pdf)
            } catch (ex: Throwable) {
                val a = 0
                val b = a
            }
        }
    }
}
