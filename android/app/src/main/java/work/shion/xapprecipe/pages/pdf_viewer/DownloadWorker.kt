package work.shion.xapprecipe.pages.pdf_viewer

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.shion.xapprecipe_core.usecases.ShowPdfUseCaseContract

class DownloadWorker(
    context: Context,
    params: WorkerParameters,
    private val showPdfUseCase: ShowPdfUseCaseContract,
) : CoroutineWorker(context, params) {

    companion object {

        private const val KEY_CACHE = "KEY_CACHE"
        private const val KEY_URL = "KEY_URL"


        fun createInputData(
            cacheName: String,
            url: String,
        ) = Data.Builder()
            .putString(KEY_CACHE, cacheName)
            .putString(KEY_URL, url)
            .build()

        fun getCacheName(data: Data) = data.getString(KEY_CACHE)
    }


    override suspend fun doWork(): Result {
        val cacheName = inputData.getString(KEY_CACHE)
        val url = inputData.getString(KEY_URL)
        if (cacheName.isNullOrBlank() || url.isNullOrBlank()) {
            return Result.failure()
        }

        return try {
            val file = withContext(Dispatchers.IO) {
                showPdfUseCase.load(cacheName, url)
            }
            if (file?.isFile == true) {
                workDataOf(
                    KEY_CACHE to cacheName,
                ).let { Result.success(it) }
            } else {
                Result.failure()
            }
        } catch (ex: Throwable) {
            Result.failure()
        }
    }
}
