package work.shion.androidrecipe.repositories

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import work.shion.androidrecipe.entities.ToDoEntity
import work.shion.androidrecipe.repositories.api_v1.APIEndpoint
import work.shion.androidrecipe.repositories.api_v1.GetToDoResponse
import work.shion.androidrecipe.repositories.api_v1.ToDo

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class ToDoRepositoryTest {

    @Test
    fun edit() = mockedServer(
            { server ->
                server.enqueue(MockResponse().apply {
                    setResponseCode(200)
                })
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl(server.url("/"))
                        .build()
                        .create(APIEndpoint::class.java)
                        .let { ToDoRepository(it) }
            }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.edit(ToDoEntity(
                        id = "1",
                        isFinish = false,
                        title = "タイトル"
                ))
            }.isSuccess).isTrue()

            Truth.assertThat(runCatching {
                target.edit(ToDoEntity(
                        id = null,
                        isFinish = false,
                        title = "タイトル"
                ))
            }.isSuccess).isFalse()
        }
    }

    @Test
    fun fetch() = mockedServer(
            { server ->
                val adapter = Moshi.Builder().build().adapter(GetToDoResponse::class.java)
                server.enqueue(MockResponse().apply {
                    setBody(adapter.toJson(GetToDoResponse(null)))
                    setResponseCode(200)
                })
                server.enqueue(MockResponse().apply {
                    setBody(adapter.toJson(GetToDoResponse(arrayListOf())))
                    setResponseCode(200)
                })
                server.enqueue(MockResponse().apply {
                    setBody(adapter.toJson(GetToDoResponse(arrayListOf(
                            ToDo(null, false, "タイトル１"),
                            ToDo("2", false, "タイトル２")
                    ))))
                    setResponseCode(200)
                })
                server.enqueue(MockResponse().apply {
                    setBody(adapter.toJson(GetToDoResponse(arrayListOf(
                            ToDo(null, false, "タイトル１"),
                            ToDo("2", false, "タイトル２"),
                            ToDo("3", null, null)
                    ))))
                    setResponseCode(200)
                })
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl(server.url("/"))
                        .build()
                        .create(APIEndpoint::class.java)
                        .let { ToDoRepository(it) }
            }
    ) { target ->
        runBlocking {
            Truth.assertThat(target.fetch().count()).isEqualTo(0)
            Truth.assertThat(target.fetch().count()).isEqualTo(0)
            Truth.assertThat(target.fetch().count()).isEqualTo(1)
            Truth.assertThat(target.fetch().count()).isEqualTo(2)
        }
    }

    @Test
    fun register() = mockedServer(
            { server ->
                server.enqueue(MockResponse().apply {
                    setResponseCode(200)
                })
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl(server.url("/"))
                        .build()
                        .create(APIEndpoint::class.java)
                        .let { ToDoRepository(it) }
            }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.register(ToDoEntity(
                        id = null,
                        isFinish = false,
                        title = "タイトル"
                ))
            }.isSuccess).isTrue()
        }
    }

    @Test
    fun remove() = mockedServer(
            { server ->
                server.enqueue(MockResponse().apply {
                    setResponseCode(200)
                })
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl(server.url("/"))
                        .build()
                        .create(APIEndpoint::class.java)
                        .let { ToDoRepository(it) }
            }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.remove(ToDoEntity(
                        id = "1",
                        isFinish = false,
                        title = "タイトル"
                ))
            }.isSuccess).isTrue()

            Truth.assertThat(runCatching {
                target.remove(ToDoEntity(
                        id = null,
                        isFinish = false,
                        title = "タイトル"
                ))
            }.isSuccess).isFalse()
        }
    }


    private fun mockedServer(
            setupper: (MockWebServer) -> IToDoRepository,
            tester: (IToDoRepository) -> Unit
    ) {
        val server = MockWebServer()
        try {
            val repository = setupper(server)
            tester(repository)
        } finally {
            server.shutdown()
        }
    }
}
