package work.shion.xapprecipe_core.usecases

import java.io.File

interface ShowPdfUseCaseContract {

    suspend fun load(
        cacheName: String,
        url: String,
    ): File?
}
