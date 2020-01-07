package work.shion.androidrecipe.repositories.api_v1

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * 通信部分のお試し環境
 */
@Config(sdk = [Build.VERSION_CODES.P])
@Ignore("お試し環境のため")
@RunWith(AndroidJUnit4::class)
class APIEndpointPlayground {

    private val api = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
            .let {
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl("http://localhost:8080")
                        .client(it)
                        .build()
                        .create(APIEndpoint::class.java)
            }


    @Test
    fun editToDo() = runBlocking {
        try {
            val id = "1"
            val request = EditToDoRequest(
                    isFinish = true,
                    title = "何か編集したToDo"
            )
            api.editToDo(id, request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun getToDo() = runBlocking {
        try {
            val result = api.getToDo()
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun registerToDo() = runBlocking {
        try {
            val request = RegisterToDoRequest(
                    title = "何か新しいToDo"
            )
            api.registerToDo(request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun removeToDo() = runBlocking {
        try {
            val id = "1"
            api.removeToDo(id)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }
}
