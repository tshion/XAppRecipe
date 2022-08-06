package com.github.tshion.xapprecipe_data.repositories

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity
import com.github.tshion.xapprecipe_core.repositories.ToDoTaskRepository
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint
import com.github.tshion.xapprecipe_data.api_xapp_v1.GetToDoResponse
import com.github.tshion.xapprecipe_data.api_xapp_v1.ToDo
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

@RunWith(AndroidJUnit4::class)
internal class ToDoTaskRepositoryAndroidTest {

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()


    @Test
    fun edit() = mockedServer(
        { server ->
            server.enqueue(MockResponse().apply {
                setResponseCode(200)
            })
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }
        }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.edit(
                    ToDoTaskEntity(
                        id = "1",
                        isFinish = false,
                        title = "タイトル",
                        updateDate = Date()
                    )
                )
            }.isSuccess).isTrue()

            Truth.assertThat(runCatching {
                target.edit(
                    ToDoTaskEntity(
                        id = "",
                        isFinish = false,
                        title = "タイトル",
                        updateDate = Date()
                    )
                )
            }.isSuccess).isFalse()
        }
    }

    @Test
    fun fetch() = mockedServer(
        { server ->
            val adapter = moshi.adapter(GetToDoResponse::class.java)
            server.enqueue(MockResponse().apply {
                setBody(adapter.toJson(GetToDoResponse(listOf())))
                setResponseCode(200)
            })
            server.enqueue(MockResponse().apply {
                setBody(adapter.toJson(GetToDoResponse(arrayListOf())))
                setResponseCode(200)
            })
            server.enqueue(MockResponse().apply {
                setBody(
                    adapter.toJson(
                        GetToDoResponse(
                            arrayListOf(
                                ToDo("", false, "タイトル１", Date()),
                                ToDo("2", false, "タイトル２", Date())
                            )
                        )
                    )
                )
                setResponseCode(200)
            })
            server.enqueue(MockResponse().apply {
                setBody(
                    adapter.toJson(
                        GetToDoResponse(
                            arrayListOf(
                                ToDo("", false, "タイトル１", Date()),
                                ToDo("2", false, "タイトル２", Date()),
                                ToDo("3", false, "", Date())
                            )
                        )
                    )
                )
                setResponseCode(200)
            })
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }
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
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }
        }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.register("タイトル")
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
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }
        }
    ) { target ->
        runBlocking {
            Truth.assertThat(runCatching {
                target.remove(
                    ToDoTaskEntity(
                        id = "1",
                        isFinish = false,
                        title = "タイトル",
                        updateDate = Date()
                    )
                )
            }.isSuccess).isTrue()

            Truth.assertThat(runCatching {
                target.remove(
                    ToDoTaskEntity(
                        id = "",
                        isFinish = false,
                        title = "タイトル",
                        updateDate = Date()
                    )
                )
            }.isSuccess).isFalse()
        }
    }


    private fun mockedServer(
        setupper: (MockWebServer) -> ToDoTaskRepository,
        tester: (ToDoTaskRepository) -> Unit
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
