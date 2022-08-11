package work.shion.xapprecipe

import android.content.Context
import com.github.tshion.xapprecipe_data.web.WebAccessor
import okhttp3.OkHttpClient
import work.shion.xapprecipe_core.usecases.BookmarkWebUseCase
import work.shion.xapprecipe_core.usecases.BookmarkWebUseCaseContract
import work.shion.xapprecipe_core.usecases.CertifyAccountUseCase
import work.shion.xapprecipe_data.inmemory.LinkMemory
import work.shion.xapprecipe_data.inmemory.TokenMemory
import work.shion.xapprecipe_data.repositories.AuthenticateRepository
import work.shion.xapprecipe_data.repositories.WebLinkRepository
import java.lang.ref.WeakReference

class ModelProvider(
    appContext: WeakReference<Context>,
) {

    val bookmarkUseCase: BookmarkWebUseCaseContract
    val certifyAccountUseCase: CertifyAccountUseCase


    init {
        val linkMemory = LinkMemory()
        val tokenMemory = TokenMemory()

        val apiWeb = WebAccessor(
            client = OkHttpClient(),
        )


        val authenticateRepository = AuthenticateRepository(
            tokenMemory = tokenMemory,
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
    }
}
