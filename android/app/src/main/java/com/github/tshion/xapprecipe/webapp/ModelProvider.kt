package com.github.tshion.xapprecipe.webapp

import android.content.Context
import okhttp3.OkHttpClient
import com.github.tshion.xapprecipe.webapp_core.usecases.*
import com.github.tshion.xapprecipe.webapp_data.file.CacheFile
import com.github.tshion.xapprecipe.webapp_data.inmemory.LinkMemory
import com.github.tshion.xapprecipe.webapp_data.inmemory.TokenMemory
import com.github.tshion.xapprecipe.webapp_data.repositories.AuthenticateRepository
import com.github.tshion.xapprecipe.webapp_data.repositories.PdfRepository
import com.github.tshion.xapprecipe.webapp_data.repositories.WebLinkRepository
import java.lang.ref.WeakReference
import com.github.tshion.xapprecipe.webapp_data.apiWeb.Api as ApiWeb

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

        val apiWeb = ApiWeb(
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
