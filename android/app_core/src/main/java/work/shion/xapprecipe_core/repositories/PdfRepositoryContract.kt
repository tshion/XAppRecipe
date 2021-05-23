package work.shion.xapprecipe_core.repositories

import java.io.File

interface PdfRepositoryContract {

    suspend fun fetch(
        name: String,
        url: String,
    ): File?

    suspend fun load(name: String): File?
}
