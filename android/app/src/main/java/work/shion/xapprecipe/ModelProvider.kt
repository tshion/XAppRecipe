package work.shion.xapprecipe

import android.content.Context
import com.github.tshion.xapprecipe_data.web.WebAccessor
import okhttp3.OkHttpClient
import work.shion.xapprecipe_core.usecases.*
import work.shion.xapprecipe_data.file.CacheFile
import work.shion.xapprecipe_data.inmemory.LinkMemory
import work.shion.xapprecipe_data.inmemory.TokenMemory
import work.shion.xapprecipe_data.repositories.AuthenticateRepository
import work.shion.xapprecipe_data.repositories.PdfRepository
import work.shion.xapprecipe_data.repositories.WebLinkRepository
import java.lang.ref.WeakReference

class ModelProvider(
    appContext: WeakReference<Context>,
) {

    val bookmarkUseCase: BookmarkWebUseCaseContract
    val certifyAccountUseCase: CertifyAccountUseCase
    val showPdfUseCase: ShowPdfUseCaseContract


    init {
        val linkMemory = LinkMemory()
        val tokenMemory = TokenMemory()

        val cacheFile = CacheFile(
            appContext = appContext,
        )

        val apiWeb = WebAccessor(
            client = OkHttpClient(),
        )


        val authenticateRepository = AuthenticateRepository(
            tokenMemory = tokenMemory,
        )
        val pdfRepository = PdfRepository(
            apiWeb = apiWeb,
            cacheFile = cacheFile,
        )
        val webLinkRepository = WebLinkRepository(
            api = apiWeb,
            linkMemory = linkMemory,
        )


        // UseCase の整備
        bookmarkUseCase = BookmarkWebUseCase(
            webLinkRepository = webLinkRepository
        )
        certifyAccountUseCase = CertifyAccountUseCase(
            authenticateRepository = authenticateRepository
        )
        showPdfUseCase = ShowPdfUseCase(
            pdfRepository = pdfRepository,
        )
    }
}
