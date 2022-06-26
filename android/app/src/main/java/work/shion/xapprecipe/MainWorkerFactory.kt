package work.shion.xapprecipe

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import work.shion.xapprecipe.pages.pdf_viewer.DownloadWorker
import work.shion.xapprecipe_core.usecases.ShowPdfUseCaseContract

class MainWorkerFactory(
    private val showPdfUseCase: ShowPdfUseCaseContract,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = when (workerClassName) {
        DownloadWorker::class.java.name -> DownloadWorker(
            context = appContext,
            params = workerParameters,
            showPdfUseCase = showPdfUseCase,
        )
        else -> null
    }
}
