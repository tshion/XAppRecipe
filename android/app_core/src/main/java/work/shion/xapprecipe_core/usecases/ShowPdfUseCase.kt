package work.shion.xapprecipe_core.usecases

import work.shion.xapprecipe_core.repositories.PdfRepositoryContract
import java.io.File

class ShowPdfUseCase(
    private val pdfRepository: PdfRepositoryContract,
) : ShowPdfUseCaseContract {

    override suspend fun load(
        cacheName: String,
        url: String,
    ): File? {
        val result = pdfRepository.load(cacheName)
        if (result?.exists() == true && result.isFile) {
            return result
        }

        return pdfRepository.fetch(cacheName, url)
    }
}
