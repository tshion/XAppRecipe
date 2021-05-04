package work.shion.xapprecipe_core.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Test
import work.shion.xapprecipe_core.entities.WebLinkEntity
import work.shion.xapprecipe_core.repositories.WebLinkRepositoryContract
import java.time.LocalDateTime

class BookmarkWebUseCaseTest {

    private val mockWebLinkRepository = object : WebLinkRepositoryContract {

        override suspend fun append(path: String) {
        }

        override suspend fun load(): List<WebLinkEntity> = emptyList()

        override suspend fun remove(id: String) {
        }

        override suspend fun update(target: WebLinkEntity) {
        }
    }


    @Test(expected = IllegalArgumentException::class)
    fun appendFailure() {
        val target = BookmarkWebUseCase(mockWebLinkRepository)
        runBlocking {
            target.append("")
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun removeFailure() {
        val target = BookmarkWebUseCase(mockWebLinkRepository)
        runBlocking {
            WebLinkEntity(
                appendDate = LocalDateTime.now(),
                description = null,
                id = "",
                imagePath = null,
                title = null,
                webPath = "",
            ).also { target.remove(it) }
        }
    }
}
